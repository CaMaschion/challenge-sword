package com.example.challenge_sword.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.challenge_sword.data.model.entity.FavouriteCat
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteCatDao {
    @Query("SELECT * FROM favourite_cats")
    fun getAllFavouriteCats(): Flow<List<FavouriteCat>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteCat(favouriteCat: FavouriteCat)

    @Delete
    suspend fun deleteFavouriteCat(favouriteCat: FavouriteCat)
}