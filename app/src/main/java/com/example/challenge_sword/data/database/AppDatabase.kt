package com.example.challenge_sword.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.challenge_sword.data.dao.FavouriteDao
import com.example.challenge_sword.data.entity.FavouriteEntity

@Database(entities = [FavouriteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteCatDao(): FavouriteDao
}