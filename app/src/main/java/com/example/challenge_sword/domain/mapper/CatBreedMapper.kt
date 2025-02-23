package com.example.challenge_sword.domain.mapper

import com.example.challenge_sword.domain.model.CatBreed
import com.example.challenge_sword.data.model.response.CatResponse
import com.example.challenge_sword.data.model.response.FavouriteResponse

class CatBreedMapper {
    fun toDomain(catResponse: CatResponse): CatBreed {
        val breed = catResponse.breeds?.firstOrNull()
        return CatBreed(
            id = catResponse.id,
            name = breed?.name.orEmpty(),
            imageUrl = catResponse.url,
            origin = breed?.origin.orEmpty(),
            temperament = breed?.temperament.orEmpty(),
            description = breed?.description.orEmpty(),
            lifeSpan = breed?.life_span.orEmpty()
        )
    }

    fun toDomain(favouriteResponse: FavouriteResponse): CatBreed {
        return CatBreed(
            id = favouriteResponse.image.id,
            name = "",
            imageUrl = favouriteResponse.image.url,
            origin = "",
            temperament = "",
            description = "",
            lifeSpan = ""
        )
    }
}
