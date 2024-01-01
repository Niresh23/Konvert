package com.niresh23.konvert.model

data class LatestResponse(
    override val success: Boolean = false,
    val base: String = "",
    val date: String = "",
    val rates: Map<String, Float> = emptyMap(),
    val timestamp: Long = 0,
) : IResponse
