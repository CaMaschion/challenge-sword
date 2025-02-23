package com.example.challenge_sword.data.model.response

data class CatResponse(
    val breeds: List<Breed>?,
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)

data class Breed(
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    val life_span: String,
)

