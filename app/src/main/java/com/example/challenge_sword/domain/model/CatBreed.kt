package com.example.challenge_sword.domain.model

data class CatBreed(
    var id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    val lifeSpan: String,
    val imageUrl: String,
    var isFavourite : Boolean = false,
) {
    fun getAverageLifeSpan(): String {
        val lifespanRange = lifeSpan.split("-")
        val lifespan = lifespanRange.lastOrNull()

        return if (lifespanRange.size == 1) {
            "Unknown"
        } else {
            lifespan.toString()
        }
    }
}
