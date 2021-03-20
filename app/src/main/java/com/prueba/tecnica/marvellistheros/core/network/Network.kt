package com.prueba.tecnica.marvellistheros.core.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

@Suppress("TooGenericExceptionCaught")
object Network {

    suspend fun <T> request(call: suspend () -> T): ResultWrapper<T> {
        return withContext(Dispatchers.IO) {
            try {
                ResultWrapper.Success(call.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> ResultWrapper.NetworkError(throwable)
                    is HttpException -> {
                        val code = throwable.code()
                        val errorResponse = throwable.localizedMessage
                        ResultWrapper.GenericError(code, errorResponse, throwable)
                    }
                    else -> {
                        ResultWrapper.GenericError(null, null, throwable)
                    }
                }
            }
        }
    }
}