package com.example.challenge_sword.data.repository

import com.example.challenge_sword.data.service.CatService
import com.example.challenge_sword.domain.mapper.CatBreedMapper
import com.example.challenge_sword.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CatRepository {
    fun getCats(): Flow<List<CatBreed>>
    fun getCatById(catId: String): Flow<CatBreed?>
    fun getFavouriteCats(subId: String): Flow<List<CatBreed>>
}

class CatRepositoryImpl @Inject constructor(
    private val api: CatService,
    private val catBreedMapper: CatBreedMapper
) : CatRepository {
    override fun getCats(): Flow<List<CatBreed>> {
        return flow {
            val catResponses = api.getCats()
            val catBreeds = catResponses.map { catBreedMapper.toDomain(it) }
            emit(catBreeds)
        }
    }

    override fun getCatById(catId: String): Flow<CatBreed?> {
        return flow {
            val catResponse = api.getCatById(catId)
            val catBreed = catResponse.let { catBreedMapper.toDomain(it) }
            emit(catBreed)
        }
    }

    override fun getFavouriteCats(subId: String): Flow<List<CatBreed>> {
        return flow {
            val favouriteResponses = api.getFavouriteCats(subId)
            val favouriteBreeds =
                favouriteResponses.map { catBreedMapper.toDomain(it) }
            emit(favouriteBreeds)
        }
    }
}
