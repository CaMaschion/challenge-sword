package com.example.challenge_sword.domain.interactor

import com.example.challenge_sword.data.repository.CatRepository
import com.example.challenge_sword.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface FavouriteInteractor {
    fun getFavouriteCats(): Flow<List<CatBreed>>
    suspend fun addFavouriteCat(catId: String)
    suspend fun removeFavouriteCat(favouriteId: String)

}

class FavouriteInteractorImpl @Inject constructor(
    private val catRepository: CatRepository
) : FavouriteInteractor {

    private val subId = "4706dc5f-9fd7-4952-bb74-8d85687f47ba"

    override fun getFavouriteCats(): Flow<List<CatBreed>> {
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

    override suspend fun addFavouriteCat(catId: String) {
        catRepository.addFavouriteCat(subId, catId)
    }

    override suspend fun removeFavouriteCat(favouriteId: String) {
        return catRepository.removeFavouriteCat(favouriteId)
    }

}
