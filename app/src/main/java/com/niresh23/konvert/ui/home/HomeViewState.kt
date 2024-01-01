package com.niresh23.konvert.ui.home

import com.niresh23.konvert.model.Rate
import com.niresh23.konvert.model.Symbol

data class HomeViewState(
    val rateList: List<Rate> = emptyList(),
    val symbolsList: List<Symbol> = emptyList(),
    val leftRate: Rate,
    val rightRate: Rate,
    val leftValue: String,
    val rightValue: String
)
