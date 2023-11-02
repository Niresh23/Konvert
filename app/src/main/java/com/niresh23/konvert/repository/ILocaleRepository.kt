package com.niresh23.konvert.repository

import com.niresh23.konvert.model.Rate
import kotlinx.coroutines.flow.Flow

interface ILocaleRepository {
    fun getAllFavoritesRates(): Flow<Result<List<Rate>>>
    fun insertFavoriteRate(rate: Rate): Flow<Result<Rate>>
    fun deleteFavoriteRate(rate: Rate): Flow<Result<Rate>>
}