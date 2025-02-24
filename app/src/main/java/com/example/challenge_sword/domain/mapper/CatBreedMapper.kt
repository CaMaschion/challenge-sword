package com.example.challenge_sword.domain.mapper

import com.example.challenge_sword.data.entity.FavouriteEntity
import com.example.challenge_sword.domain.model.CatBreed
import com.example.challenge_sword.data.model.response.CatResponse

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

    fun toDomain(favouriteEntity: FavouriteEntity): CatBreed {
        return CatBreed(
            id = favouriteEntity.id,
            name = favouriteEntity.name,
            imageUrl = favouriteEntity.url,
            origin = "",
            temperament = "",
            description = "",
            lifeSpan = favouriteEntity.lifeSpan,
            isFavourite = false
        )
    }
}
