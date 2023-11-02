package com.niresh23.konvert.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.niresh23.konvert.model.Rate

@Dao
interface FavoriteRateDao {
    @Insert
    suspend fun insert(rate: Rate)

    @Query("SELECT * FROM rate")
    suspend fun getAllFavoriteRates(): List<Rate>

    @Delete
    suspend fun deleteFavoriteRate(rate: Rate)
}