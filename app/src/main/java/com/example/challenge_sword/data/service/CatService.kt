package com.example.challenge_sword.data.service

import com.example.challenge_sword.data.model.request.FavouriteRequest
import com.example.challenge_sword.data.model.response.CatResponse
import com.example.challenge_sword.data.model.response.FavouriteAddResponse
import com.example.challenge_sword.data.model.response.FavouriteResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CatService {
    @Headers("x-api-key: live_dLLr4LPMOFwVwl31R3rjNrYg5ixhIZG5kq5iE8HRZrKIiasrZbORw5HjqT8HsJhB")
    @GET("v1/images/search?limit=100&has_breeds=1&page=1")
    suspend fun getCats(): List<CatResponse>

    @Headers("x-api-key: live_dLLr4LPMOFwVwl31R3rjNrYg5ixhIZG5kq5iE8HRZrKIiasrZbORw5HjqT8HsJhB")
    @GET("v1/images/{catId}")
    suspend fun getCatById(@Path("catId") catId: String): CatResponse

    @Headers("x-api-key: live_dLLr4LPMOFwVwl31R3rjNrYg5ixhIZG5kq5iE8HRZrKIiasrZbORw5HjqT8HsJhB")
    @GET("v1/favourites?limit=20")
    suspend fun getFavouriteCats(
        @Query("sub_id") subId: String
    ): List<FavouriteResponse>

    @Headers("x-api-key: live_dLLr4LPMOFwVwl31R3rjNrYg5ixhIZG5kq5iE8HRZrKIiasrZbORw5HjqT8HsJhB")
    @POST("v1/favourites")
    suspend fun addFavouriteCat(@Body favouriteRequest: FavouriteRequest): FavouriteAddResponse

    @Headers("x-api-key: live_dLLr4LPMOFwVwl31R3rjNrYg5ixhIZG5kq5iE8HRZrKIiasrZbORw5HjqT8HsJhB")
    @DELETE("v1/favourites/{favouriteId}")
    suspend fun removeFavouriteCat(@Path("favouriteId") favouriteId: String)

}
