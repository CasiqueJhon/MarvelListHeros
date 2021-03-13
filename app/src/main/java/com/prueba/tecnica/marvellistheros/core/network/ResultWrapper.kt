package com.prueba.tecnica.marvellistheros.core.network

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()

    data class GenericError(
            val code: Int? = null,
            val error: String? = null,
            val throwable: Throwable
    ): ResultWrapper<Nothing>()

    data class NetworkError(val throwable: Throwable): ResultWrapper<Nothing>()
}