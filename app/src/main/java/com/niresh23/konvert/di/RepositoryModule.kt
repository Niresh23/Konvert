package com.niresh23.konvert.di

import com.niresh23.konvert.repository.ILocaleRepository
import com.niresh23.konvert.repository.IRemoteRepository
import com.niresh23.konvert.repository.LocalRepositoryImpl
import com.niresh23.konvert.repository.RemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module(includes = [HttpModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repository: RemoteRepositoryImpl): IRemoteRepository

    @Binds
    abstract fun bindsLocalRepository(repository: LocalRepositoryImpl): ILocaleRepository
}