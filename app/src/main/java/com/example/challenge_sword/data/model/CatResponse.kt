package com.example.challenge_sword.data.model

data class Cat(
    val id: String,
    val url: String,
    val breeds: List<Breed>
)

data class Breed (
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
)
