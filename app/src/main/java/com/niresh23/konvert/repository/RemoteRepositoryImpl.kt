package com.niresh23.konvert.repository

import com.niresh23.konvert.common.safeApiCall
import com.niresh23.konvert.model.Rate
import com.niresh23.konvert.model.Symbol
import com.niresh23.konvert.api.ExchangeratesService
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class RemoteRepositoryImpl @Inject constructor(
    private val service: ExchangeratesService,
) : IRemoteRepository {
    override fun getSymbols(): Flow<Result<List<Symbol>>> =
        safeApiCall { service.getSymbols() }
            .map { result ->
                result.map { response ->
                    if (response.success) {
                        response.symbols.entries.map { entry ->
                            Symbol(entry.key, entry.value)
                        }
                    } else {
                        emptyList()
                    }
            }
        }

    override fun getRates(base: String, symbols: String): Flow<Result<List<Rate>>> =
        safeApiCall { service.getLatest(base, symbols) }
            .map { result ->
                result.map { response ->
                    if (response.success) {
                        response.rates.entries.map { entry ->
                            Rate(entry.key, entry.value)
                        }
                    } else {
                        emptyList()
                    }
                }
            }
}