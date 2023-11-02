package com.niresh23.konvert.di

import android.content.Context
import androidx.room.Room
import com.niresh23.konvert.database.FavoriteRateDao
import com.niresh23.konvert.database.KonvertDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun getDatabaseInstance(@ApplicationContext context: Context): KonvertDatabase =
        Room.databaseBuilder(context, KonvertDatabase::class.java, "konvert-database").build()

    @Provides
    fun getFavoriteRateDao(database: KonvertDatabase): FavoriteRateDao = database.favoriteRateDao()
}