package com.niresh23.konvert.repository

import com.niresh23.konvert.model.RateEntity
import com.niresh23.konvert.model.Symbol
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getRates(base: String): Flow<Result<List<RateEntity>>>

    fun getSymbols(): Flow<Result<List<Symbol>>>
}