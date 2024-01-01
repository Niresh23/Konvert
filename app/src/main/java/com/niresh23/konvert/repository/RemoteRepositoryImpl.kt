package com.niresh23.konvert.repository

import com.niresh23.konvert.common.safeApiCall
import com.niresh23.konvert.api.ExchangeRatesService
import com.niresh23.konvert.model.LatestResponse
import com.niresh23.konvert.model.SymbolsResponse
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class RemoteRepositoryImpl @Inject constructor(
    private val service: ExchangeRatesService,
) : IRemoteRepository {
    override fun getSymbols(): Flow<Result<SymbolsResponse>> =
        safeApiCall { service.getSymbols() }

    override fun getLatestRates(base: String): Flow<Result<LatestResponse>> =
        safeApiCall { service.getLatest(base) }
}