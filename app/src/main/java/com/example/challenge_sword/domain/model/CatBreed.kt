package com.example.challenge_sword.domain.model

data class CatBreed(
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    val lifeSpan: String,
    val imageUrl: String,
    val favouriteId : Long? = null,
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
