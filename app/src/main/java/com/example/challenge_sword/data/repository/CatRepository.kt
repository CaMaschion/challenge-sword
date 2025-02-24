package com.example.challenge_sword.data.repository

import com.example.challenge_sword.data.dao.FavouriteDao
import com.example.challenge_sword.data.entity.FavouriteEntity
import com.example.challenge_sword.data.service.CatService
import com.example.challenge_sword.domain.mapper.CatBreedMapper
import com.example.challenge_sword.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CatRepository {
    fun getCats(): Flow<List<CatBreed>>
    fun getCatById(catId: String): Flow<CatBreed?>
    fun getFavouriteCats(): Flow<List<CatBreed>>
    fun getFavouriteCatById(id: String): Flow<CatBreed?>
    suspend fun insertFavouriteCat(cat: FavouriteEntity)
    suspend fun deleteFavouriteCat(cat: FavouriteEntity)
}

class CatRepositoryImpl @Inject constructor(
    private val api: CatService,
    private val catBreedMapper: CatBreedMapper,
    private val favouriteDao: FavouriteDao
) : CatRepository {
    override fun getCats(): Flow<List<CatBreed>> {
        return flow {
            val catResponses = api.getCats()
            val catBreeds = catResponses.map { catBreedMapper.toDomain(it) }
            emit(catBreeds)
        }
    }

    override fun getCatById(catId: String): Flow<CatBreed> {
        return flow {
            val catResponse = api.getCatById(catId)
            val catBreed = catResponse.let { catBreedMapper.toDomain(it) }
            emit(catBreed)
        }
    }

    override fun getFavouriteCats(): Flow<List<CatBreed>> {
        return flow {
            val favouriteCats = favouriteDao.getAllFavouriteCats()
            favouriteCats.collect { cats ->
                emit(cats.map { catBreedMapper.toDomain(it) })
            }
        }
    }

    override suspend fun insertFavouriteCat(cat: FavouriteEntity) {
        favouriteDao.insertFavouriteCat(cat)
    }

    override suspend fun deleteFavouriteCat(cat: FavouriteEntity) {
        favouriteDao.deleteFavouriteCat(cat)
    }

    override fun getFavouriteCatById(id: String): Flow<CatBreed?> {
        return flow {
            favouriteDao.getFavouriteCatById(id).collect { cat ->
                emit(cat?.let { catBreedMapper.toDomain(it) })
            }
        }
    }
}
