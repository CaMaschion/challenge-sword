package com.example.challenge_sword.data.service

import com.example.challenge_sword.data.model.CatResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CatService {
    @Headers("x-api-key: live_dLLr4LPMOFwVwl31R3rjNrYg5ixhIZG5kq5iE8HRZrKIiasrZbORw5HjqT8HsJhB")
    @GET("v1/images/search?limit=100&has_breeds=1&page=1")
    suspend fun getCats(): List<CatResponse>

    @Headers("x-api-key: live_dLLr4LPMOFwVwl31R3rjNrYg5ixhIZG5kq5iE8HRZrKIiasrZbORw5HjqT8HsJhB")
    @GET("v1/images/{catId}")
    suspend fun getCatById(@Path("catId") catId: String): CatResponse
}