package com.niresh23.konvert.ui.rate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niresh23.konvert.model.Rate
import com.niresh23.konvert.model.Symbol
import com.niresh23.konvert.repository.ILocaleRepository
import com.niresh23.konvert.repository.IRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val remoteRepository: IRemoteRepository,
    private val localRepository: ILocaleRepository
): ViewModel() {
    private val _state = MutableStateFlow(RateState())
    val state = _state.asStateFlow()

    private val _ratesListFlow = MutableSharedFlow<List<Rate>>()
    val ratesListFlow = _ratesListFlow.asSharedFlow()

    private val _symbolsListFlow = MutableSharedFlow<List<Symbol>>()
    val symbolsListFlow = _symbolsListFlow.asSharedFlow()

    fun onBaseChanged(base: String) {
        _state.value = _state.value.copy(base = base)
    }

    fun onSymbolsChanged(symbols: String) {
        _state.value = _state.value.copy(symbols = symbols)
    }

    fun onRateFavoriteClicked(rate: Rate) {
        viewModelScope.launch {
            val flow = if (rate.isFavorite) {
                rate.isFavorite = false
                localRepository.deleteFavoriteRate(rate)
            } else {
                rate.isFavorite = true
                localRepository.insertRate(rate)
            }

            flow.collectLatest { result ->
                if (result.isSuccess) {
                    val list = _state.value.rateList.toMutableList()
                    list.forEach {
                        if (it.code == result.getOrNull()?.code) {
                            it.isFavorite = rate.isFavorite
                        }
                    }
                    _state.value = _state.value.copy(rateList = list)
                    _ratesListFlow.emit(list)
                } else {

                }
            }
        }
    }

    fun onCreateView() {
        val base = _state.value.base
        val symbols = _state.value.symbols

        viewModelScope.launch {
            remoteRepository.getRates(base, symbols).zip(localRepository.getAllRates()) { remoteResult, localResul ->
                if(remoteResult.isSuccess) {
                    val favoriteList = localResul.getOrDefault(emptyList()).map { it.code }
                    remoteResult.map { remoteList ->
                        remoteList.map {
                            it.isFavorite = favoriteList.contains(it.code)
                            it
                        }
                    }
                } else {
                    remoteResult
                }
            }.collectLatest { result ->
                if (result.isSuccess) {
                    val data = result.getOrDefault(emptyList())
                    _state.value = _state.value.copy(rateList = data)
                    _ratesListFlow.emit(data)
                } else {
                    result.exceptionOrNull()
                }

                this.cancel()
            }
        }
    }

    fun requestLatestRate() {
        val base = _state.value.base
        val symbols = _state.value.symbols

        viewModelScope.launch {
            remoteRepository.getRates(base, symbols).zip(localRepository.getAllRates()) { remoteResult, localResul ->
                if(remoteResult.isSuccess) {
                    val favoriteList = localResul.getOrDefault(emptyList()).map { it.code }
                    remoteResult.map { remoteList ->
                        remoteList.map {
                            it.isFavorite = favoriteList.contains(it.code)
                            it
                        }
                    }
                } else {
                    remoteResult
                }
            }.collectLatest { result ->
                if (result.isSuccess) {
                    val data = result.getOrDefault(emptyList())
                    _state.value = _state.value.copy(rateList = data)
                    _ratesListFlow.emit(data)
                } else {
                    result.exceptionOrNull()
                }

                this.cancel()
            }
        }
    }

    fun requestSymbols() {
        viewModelScope.launch {
            remoteRepository.getSymbols().collectLatest { result ->
                if (result.isSuccess) {
                    val data = result.getOrDefault(emptyList())
                    _state.value = _state.value.copy(symbolsList = data)
                    _symbolsListFlow.emit(data)
                }
            }
        }
    }
}

data class RateState(
    val rateList: List<Rate> = emptyList(),
    val symbolsList: List<Symbol> = emptyList(),
    val base: String = "RUB",
    val symbols: String = ""
)