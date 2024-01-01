package com.niresh23.konvert.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.niresh23.konvert.model.FavoriteEntity
import com.niresh23.konvert.model.RateEntity

@Database(entities = [RateEntity::class, FavoriteEntity::class], version = 1, exportSchema = false)
abstract class KonvertDatabase: RoomDatabase() {
    abstract fun rateDao(): RateDao
    abstract fun favoriteDao(): FavoriteDao
}