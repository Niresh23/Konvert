package com.niresh23.konvert.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.niresh23.konvert.model.Rate

@Database(entities = [Rate::class], version = 1)
abstract class KonvertDatabase: RoomDatabase() {
    abstract fun favoriteRateDao(): FavoriteRateDao
}