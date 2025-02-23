package com.example.challenge_sword.data.model.response

data class FavouriteResponse(
    val id: Long,
    val image_id: String,
    val sub_id: String?,
    val image: Image
)

data class Image(
    val id: String,
    val url: String
)
