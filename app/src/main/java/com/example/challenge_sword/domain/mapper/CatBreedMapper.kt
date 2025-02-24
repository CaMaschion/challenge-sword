package com.example.challenge_sword.domain.mapper

import com.example.challenge_sword.data.model.entity.FavouriteCat
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

    fun toDomain(favouriteCat: FavouriteCat): CatBreed {
        return CatBreed(
            id = favouriteCat.id,
            name = favouriteCat.name,
            imageUrl = favouriteCat.url,
            origin = "",
            temperament = "",
            description = "",
            lifeSpan = favouriteCat.lifeSpan,
            favouriteId = 0
        )
    }

    fun toEntity(catBreed: CatBreed): FavouriteCat {
        return FavouriteCat(
            id = catBreed.id,
            name = catBreed.name,
            url = catBreed.imageUrl,
            lifeSpan = catBreed.lifeSpan
        )
    }
}
