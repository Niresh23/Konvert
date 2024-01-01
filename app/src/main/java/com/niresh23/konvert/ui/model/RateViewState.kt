package com.niresh23.konvert.ui.model

data class RateViewState(
    val name: String,
    val code: String,
    val value: Float,
    val isFavorite: Boolean = false
)
