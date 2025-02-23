package com.example.challenge_sword.domain.interactor

import com.example.challenge_sword.data.repository.CatRepository
import com.example.challenge_sword.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface FavouriteInteractor {
    fun getFavouriteCats(subId: String): Flow<List<CatBreed>>

}

class FavouriteInteractorImpl @Inject constructor(
    private val catRepository: CatRepository
) : FavouriteInteractor {

    override fun getFavouriteCats(subId: String): Flow<List<CatBreed>> {
        return flow {
            val favouriteCats = catRepository.getFavouriteCats(subId)
            val detailedCats = mutableListOf<CatBreed>()
            favouriteCats.collect { cats ->
                cats.forEach { cat ->
                    val catDetail = catRepository.getCatById(cat.id).map { it }
                    catDetail.collect { detailedCat ->
                        if (detailedCat != null) {
                            detailedCats.add(detailedCat)
                        }
                    }
                }
                emit(detailedCats)
            }
        }
    }
}
