package com.niresh23.konvert.ui.home

import androidx.lifecycle.ViewModel
import com.niresh23.konvert.model.Rate
import com.niresh23.konvert.model.Symbol
import com.niresh23.konvert.repository.IRemoteRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val remoteRepository: IRemoteRepository,
    private val localRepository: IRemoteRepository
): ViewModel() {
    private val _homeViewState = MutableStateFlow(
        HomeViewState(
            leftRate = Rate(code = "RUB"),
            rightRate = Rate(code = "USD"),
            leftValue = "0",
            rightValue = "0"
        )
    )

    private val _ratesListFlow = MutableSharedFlow<List<Rate>>()
    val ratesListFlow = _ratesListFlow.asSharedFlow()

    private val _symbolsListFlow = MutableSharedFlow<List<Symbol>>()
    val symbolsListFlow = _symbolsListFlow.asSharedFlow()

    fun leftCurrencyChanged(value: String) {
        val leftValue = formatValue(value)
        val rightValue = calculateRightValue(leftValue.toFloat()).toString()
        _homeViewState.value = _homeViewState.value.copy(leftValue = leftValue, rightValue = rightValue)
    }

    fun rightCurrencyChanged(value: String) {
        val rightValue = formatValue(value)
        val leftValue = calculateLeftValue(value.toFloat()).toString()
        _homeViewState.value = _homeViewState.value.copy(leftValue = leftValue, rightValue = rightValue)
    }

    fun swapClicked() {
        val homeViewState = _homeViewState.value
        val rightSwappedRate = homeViewState.leftRate
        val leftSwappedRate = homeViewState.rightRate
        val leftSwappedValue = homeViewState.rightValue
        val rightSwappedValue = homeViewState.leftValue
        _homeViewState.value = _homeViewState.value.copy(
            leftRate = leftSwappedRate,
            rightRate = rightSwappedRate,
            leftValue = leftSwappedValue,
            rightValue = rightSwappedValue
            )
    }

    private fun formatValue(value: String): String {
        val stringBuilder = StringBuilder()
        if (value.startsWith(".")) stringBuilder.append("0").append(value)
        else stringBuilder.append(value)
        return stringBuilder.toString()
    }

    private fun calculateRightValue(leftValue: Float): Float {
        return _homeViewState.value.rightRate.value * leftValue
    }

    private fun calculateLeftValue(rightValue: Float): Float {
        return rightValue / _homeViewState.value.rightRate.value
    }
}