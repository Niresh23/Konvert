package com.niresh23.konvert.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.niresh23.konvert.model.FavoriteEntity

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)
    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)
}