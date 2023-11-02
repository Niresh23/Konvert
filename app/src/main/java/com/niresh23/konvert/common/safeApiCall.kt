package com.niresh23.konvert.common

import com.google.gson.Gson
import com.niresh23.konvert.model.ErrorResponse
import com.niresh23.konvert.model.IResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

fun <T: IResponse> safeApiCall(apiCall: suspend () -> T): Flow<Result<T>> {
    return flow {
        try {
            val result = apiCall.invoke()
            emit(Result.success(result))
        } catch (throwable: Throwable) {
            val error: HttpError = when(throwable) {
                is IOException -> HttpError.ConnectionError
                is HttpException -> {
                    val code = throwable.code()

                    when(code) {
                        401 -> HttpError.UnauthorizedError
                        404 -> HttpError.NotFoundError
                        in 500 ..599 -> HttpError.ServerError
                        else -> {
                            val response = throwable.response()

                            val errorBody = response?.errorBody()?.string()
                            val errorResponse = try {
                                Gson().fromJson(errorBody, ErrorResponse::class.java)
                            } catch (_: Exception) {
                                null
                            }

                            HttpError.GeneralError(errorResponse?.error?.message ?: "")
                        }
                    }
                }
                else -> HttpError.UnknownError
            }

            emit(Result.failure(error))
        }
    }.flowOn(Dispatchers.IO)
}