package com.niresh23.konvert.repository

import com.niresh23.konvert.model.FavoriteEntity
import com.niresh23.konvert.model.Rate
import com.niresh23.konvert.model.RateEntity
import kotlinx.coroutines.flow.Flow

interface ILocaleRepository {
    fun getAllRates(): Flow<Result<List<RateEntity>>>
    fun insertRate(rate: RateEntity): Flow<Result<RateEntity>>
    fun insertRatesList(rates: List<RateEntity>): Flow<Result<List<RateEntity>>>
    fun insertFavorite(rate: RateEntity): Flow<Result<RateEntity>>
    fun deleteFavoriteRate(rate: RateEntity): Flow<Result<RateEntity>>
}