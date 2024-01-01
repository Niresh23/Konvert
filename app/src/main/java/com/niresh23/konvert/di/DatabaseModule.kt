package com.niresh23.konvert.di

import android.content.Context
import androidx.room.Room
import com.niresh23.konvert.database.FavoriteDao
import com.niresh23.konvert.database.RateDao
import com.niresh23.konvert.database.KonvertDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun getDatabaseInstance(@ApplicationContext context: Context): KonvertDatabase =
        Room.databaseBuilder(context, KonvertDatabase::class.java, "konvert-database").build()

    @Provides
    fun getRateDao(database: KonvertDatabase): RateDao = database.rateDao()

    @Provides
    fun getFavoriteDao(database: KonvertDatabase): FavoriteDao = database.favoriteDao()
}