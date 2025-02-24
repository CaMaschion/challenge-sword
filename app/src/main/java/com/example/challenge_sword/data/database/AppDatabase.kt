package com.example.challenge_sword.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.challenge_sword.data.dao.FavouriteCatDao
import com.example.challenge_sword.data.model.entity.FavouriteCat

@Database(entities = [FavouriteCat::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteCatDao(): FavouriteCatDao
}