package com.niresh23.konvert.repository

import com.niresh23.konvert.common.safeDatabaseCall
import com.niresh23.konvert.database.FavoriteDao
import com.niresh23.konvert.database.RateDao
import com.niresh23.konvert.model.FavoriteEntity
import com.niresh23.konvert.model.Rate
import com.niresh23.konvert.model.RateEntity
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class LocalRepositoryImpl @Inject constructor(
    private val rateDao: RateDao,
    private val favoriteDao: FavoriteDao
): ILocaleRepository {
    override fun getAllRates(): Flow<Result<List<RateEntity>>> = safeDatabaseCall {
        rateDao.getAllRates()
    }

    override fun insertRate(rate: RateEntity): Flow<Result<RateEntity>> = safeDatabaseCall {
        rateDao.insert(rate)
    }.map { result -> result.map { rate } }

    override fun insertRatesList(rates: List<RateEntity>): Flow<Result<List<RateEntity>>> = safeDatabaseCall {
        rateDao.insert(rates)
    }.map { result -> result.map { rates } }

    override fun insertFavorite(rate: RateEntity): Flow<Result<RateEntity>> = safeDatabaseCall {
        val favoriteEntity = FavoriteEntity(rate.code)
        favoriteDao.insertFavorite(favoriteEntity)
    }.map { it.map { rate } }

    override fun deleteFavoriteRate(rate: RateEntity): Flow<Result<RateEntity>> = safeDatabaseCall {
        val favoriteEntity = FavoriteEntity(rate.code)
        favoriteDao.deleteFavorite(favoriteEntity)
    }.map { result -> result.map { rate } }
}