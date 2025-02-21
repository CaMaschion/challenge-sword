package com.example.challenge_sword.data.service

import com.example.challenge_sword.data.model.CatResponse
import javax.inject.Inject

interface CatApi {
    suspend fun getCats(): List<CatResponse>
}

class CatApiImpl @Inject constructor(private val catService: CatService) : CatApi {
    override suspend fun getCats() = catService.getCats()
}