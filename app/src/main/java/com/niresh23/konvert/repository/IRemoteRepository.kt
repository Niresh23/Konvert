package com.niresh23.konvert.repository

import com.niresh23.konvert.model.Rate
import com.niresh23.konvert.model.Symbol
import kotlinx.coroutines.flow.Flow

interface IRemoteRepository {
    fun getSymbols(): Flow<Result<List<Symbol>>>
    fun getRates(base: String, symbols: String): Flow<Result<List<Rate>>>
}