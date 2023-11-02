package com.niresh23.konvert.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

inline fun <T> safeDatabaseCall(crossinline databaseCall: suspend () -> T): Flow<Result<T>> {
    return flow {
        try {
            val result = databaseCall.invoke()
            emit(Result.success(result))
        } catch (throwable: Throwable) {
            emit(Result.failure(throwable))
        }
    }.flowOn(Dispatchers.IO)
}