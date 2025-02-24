package com.example.challenge_sword.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.challenge_sword.data.entity.FavouriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {
    @Query("SELECT * FROM favourite_cats")
    fun getAllFavouriteCats(): Flow<List<FavouriteEntity>>

    @Query("SELECT * FROM favourite_cats WHERE id = :id")
    fun getFavouriteCatById(id: String): Flow<FavouriteEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteCat(favouriteEntity: FavouriteEntity)

    @Delete
    suspend fun deleteFavouriteCat(favouriteEntity: FavouriteEntity)

}