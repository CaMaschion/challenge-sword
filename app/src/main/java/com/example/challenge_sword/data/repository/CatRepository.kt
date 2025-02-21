package com.example.challenge_sword.data.repository

import com.example.challenge_sword.data.model.CatResponse
import com.example.challenge_sword.data.service.CatService
import jakarta.inject.Inject

interface CatRepository {
    suspend fun getCats(): List<CatResponse>
}

class CatRepositoryImpl @Inject constructor (private val api: CatService) : CatRepository {
    override suspend fun getCats(): List<CatResponse> {
        return api.getCats()
    }
}

