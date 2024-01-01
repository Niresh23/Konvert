package com.niresh23.konvert.repository

import com.niresh23.konvert.model.LatestResponse
import com.niresh23.konvert.model.Rate
import com.niresh23.konvert.model.Symbol
import com.niresh23.konvert.model.SymbolsResponse
import kotlinx.coroutines.flow.Flow

interface IRemoteRepository {
    fun getSymbols(): Flow<Result<SymbolsResponse>>
    fun getLatestRates(base: String): Flow<Result<LatestResponse>>
}