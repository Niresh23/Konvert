package com.niresh23.konvert.api

import com.niresh23.konvert.model.LatestResponse
import com.niresh23.konvert.model.SymbolsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeratesService {
    @GET("symbols")
    suspend fun getSymbols(): SymbolsResponse

    @GET("latest")
    suspend fun getLatest(@Query("base") base: String, @Query("symbols") symbols: String): LatestResponse
}