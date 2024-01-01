package com.niresh23.konvert.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.niresh23.konvert.model.FavoriteEntity
import com.niresh23.konvert.model.RateEntity

@Dao
interface RateDao {
    @Insert
    suspend fun insert(rate: RateEntity)
    @Insert
    suspend fun insert(rates: List<RateEntity>)

    @Query("SELECT * FROM rate_table")
    suspend fun getAllRates(): List<RateEntity>
}