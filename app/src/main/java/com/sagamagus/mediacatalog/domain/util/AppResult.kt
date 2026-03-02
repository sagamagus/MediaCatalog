package com.sagamagus.mediacatalog.domain.util

sealed class AppResult<out T> {

    data class Success<T>(val data: T) : AppResult<T>()

    data class Error(
        val message: String,
        val type: ErrorType
    ) : AppResult<Nothing>()
}

enum class ErrorType {
    NETWORK,
    HTTP,
    SERIALIZATION,
    UNKNOWN
}