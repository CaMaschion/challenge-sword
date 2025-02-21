package com.example.challenge_sword.data.repository

import com.example.challenge_sword.data.model.CatResponse
import com.example.challenge_sword.data.service.CatService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CatRepository {
    fun getCats(): Flow<List<CatResponse>>
}

class CatRepositoryImpl @Inject constructor(private val api: CatService) : CatRepository {
    override fun getCats(): Flow<List<CatResponse>> {
        return flow {
            emit(api.getCats())
        }
    }
}
