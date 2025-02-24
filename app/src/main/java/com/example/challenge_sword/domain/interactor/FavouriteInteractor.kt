package com.example.challenge_sword.domain.interactor

import com.example.challenge_sword.data.repository.CatRepository
import com.example.challenge_sword.domain.mapper.CatBreedMapper
import com.example.challenge_sword.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface FavouriteInteractor {
    fun getFavouriteCats(): Flow<List<CatBreed>>
    suspend fun addFavouriteCat(cat: CatBreed)
    suspend fun removeFavouriteCat(cat: CatBreed)
}

class FavouriteInteractorImpl @Inject constructor(
    private val catRepository: CatRepository,
) : FavouriteInteractor {

    private val detailedCats = mutableListOf<CatBreed>()

    override fun getFavouriteCats(): Flow<List<CatBreed>> {
        return flow {
            val favouriteCats = catRepository.getFavouriteCats()
            favouriteCats.collect { cats ->
                detailedCats.clear()
                detailedCats.addAll(cats)
                emit(cats)
            }
        }
    }

    override suspend fun addFavouriteCat(cat: CatBreed) {
        cat.let { catRepository.insertFavouriteCat(CatBreedMapper().toEntity(it)) }
    }

    override suspend fun removeFavouriteCat(cat: CatBreed) {
        cat.let {
            val entity = CatBreedMapper().toEntity(it)
            catRepository.deleteFavouriteCat(entity)
        }
    }
}
