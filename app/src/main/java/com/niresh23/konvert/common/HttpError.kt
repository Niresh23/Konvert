package com.niresh23.konvert.common

sealed class HttpError: Throwable() {
    object ConnectionError: HttpError()
    object ServerError: HttpError()
    object NotFoundError: HttpError()
    object UnauthorizedError: HttpError()
    data class GeneralError(override val message: String): HttpError()
    object UnknownError: HttpError()
}
