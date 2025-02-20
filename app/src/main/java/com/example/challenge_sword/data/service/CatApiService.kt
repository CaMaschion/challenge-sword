package com.example.challenge_sword.data.service

import com.example.challenge_sword.data.model.Cat
import retrofit2.http.GET
import retrofit2.http.Headers

interface CatApiService {
    @Headers("x-api-key: live_dLLr4LPMOFwVwl31R3rjNrYg5ixhIZG5kq5iE8HRZrKIiasrZbORw5HjqT8HsJhB")
    @GET("v1/images/search")
    suspend fun getCat(): List<Cat>
}