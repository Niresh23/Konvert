package com.niresh23.konvert.repository

import com.niresh23.konvert.common.safeDatabaseCall
import com.niresh23.konvert.database.FavoriteRateDao
import com.niresh23.konvert.model.Rate
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class LocalRepositoryImpl @Inject constructor(
    private val dao: FavoriteRateDao
): ILocaleRepository {
    override fun getAllFavoritesRates(): Flow<Result<List<Rate>>> = safeDatabaseCall {
        dao.getAllFavoriteRates()
    }

    override fun insertFavoriteRate(rate: Rate): Flow<Result<Rate>> = safeDatabaseCall {
        dao.insert(rate)
    }.map { result -> result.map { rate } }

    override fun deleteFavoriteRate(rate: Rate): Flow<Result<Rate>> = safeDatabaseCall {
        dao.deleteFavoriteRate(rate)
    }.map { result -> result.map { rate } }
}