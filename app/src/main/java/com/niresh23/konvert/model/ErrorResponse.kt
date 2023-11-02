package com.niresh23.konvert.model

data class ErrorResponse(
    val error: Error
) {
    data class Error(val code: String, val message: String)
}
