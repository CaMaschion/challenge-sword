package com.example.challenge_sword.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_cats")
data class FavouriteCat(
    @PrimaryKey val id: String,
    val name: String,
    val url: String,
    val lifeSpan: String,
)
