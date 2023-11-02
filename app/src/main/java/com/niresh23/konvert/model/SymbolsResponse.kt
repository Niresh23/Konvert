package com.niresh23.konvert.model

data class SymbolsResponse(
    override val success: Boolean = false,
    val symbols: Map<String, String> = emptyMap(),
): IResponse
