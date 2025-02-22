package com.example.challenge_sword.data.model

data class FavouriteResponse(
    val id: Long,
    val imageId: String,
    val subId: String?,
    val image: Image
)

data class Image(
    val id: String,
    val url: String
)
