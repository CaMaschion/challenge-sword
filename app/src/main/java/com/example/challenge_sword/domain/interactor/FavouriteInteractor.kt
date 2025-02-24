package com.example.challenge_sword.domain.interactor

import com.example.challenge_sword.data.entity.FavouriteEntity
import com.example.challenge_sword.data.repository.CatRepository
import com.example.challenge_sword.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface FavouriteInteractor {
    fun getFavouriteCats(): Flow<List<CatBreed>>
    fun getFavouriteCatById(id: String): Flow<CatBreed?>
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

    override fun getFavouriteCatById(id: String): Flow<CatBreed?> {
        return flow {
            catRepository.getFavouriteCatById(id).collect { cat ->
                emit(cat)
            }
        }
    }

    override suspend fun addFavouriteCat(cat: CatBreed) {
        cat.let {
            val entity = toEntity(it)
            catRepository.insertFavouriteCat(entity)
        }
    }

    override suspend fun removeFavouriteCat(cat: CatBreed) {
        cat.let {
            val entity = toEntity(it)
            catRepository.deleteFavouriteCat(entity)
        }
    }

    private fun toEntity(catBreed: CatBreed): FavouriteEntity {
        return FavouriteEntity(
            id = catBreed.id,
            name = catBreed.name,
            url = catBreed.imageUrl,
            lifeSpan = catBreed.lifeSpan
        )
    }
}
