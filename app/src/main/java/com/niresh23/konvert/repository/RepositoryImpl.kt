package com.niresh23.konvert.repository

import com.niresh23.konvert.common.Constants
import com.niresh23.konvert.model.RateEntity
import com.niresh23.konvert.model.Symbol
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteRepository: IRemoteRepository,
    private val localRepository: ILocaleRepository
) : IRepository {
    override fun getRates(base: String): Flow<Result<List<RateEntity>>> {
        return localRepository.getAllRates().transform { rateEntity ->
            emit(rateEntity)

            remoteRepository.getLatestRates(Constants.BASE_SYMBOL).zip(remoteRepository.getSymbols()) { latestResponseResult, symbolsResponseResult ->
                if (latestResponseResult.isSuccess && symbolsResponseResult.isSuccess) {
                    val symbolsMap = symbolsResponseResult.getOrNull()?.symbols

                   latestResponseResult.onSuccess { latestResponse ->
                       val rates = latestResponse.rates.map {
                           RateEntity(
                               it.key,
                               it.value,
                               latestResponse.timestamp,
                               symbolsMap?.get(it.key) ?: ""
                           )
                       }

                       emit(localRepository.insertRatesList(rates).first())
                    }

                } else if(latestResponseResult.isFailure) {
                    latestResponseResult
                } else {
                    symbolsResponseResult
                }
            }
        }
    }

    override fun getSymbols(): Flow<Result<List<Symbol>>> {
        return localRepository.getAllRates().map { result ->
            result.map { ratesList ->
                ratesList.map { Symbol(it.code, it.name) }
            }
        }
    }
}