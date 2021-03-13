package com.prueba.tecnica.marvellistheros.core.network

import com.prueba.tecnica.marvellistheros.core.plataform.Config.isNetworkConnected
import okhttp3.Interceptor

const val CACHE_MAX_AGE = 5000
const val CACHE_MAX_STALE = 60 * 60 * 24 * 7

class CacheInterceptor {
    var onlineInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge = CACHE_MAX_AGE // read from cache

        response.newBuilder()
                .header("Cache_Control", "public, max-age=$maxAge")
                .removeHeader("Pragma")
                .build()
    }

    var offlineInterceptor = Interceptor { chain ->
        var request = chain.request()

        if (!isNetworkConnected) {
            val maxStale = CACHE_MAX_STALE

            request = request.newBuilder()
                    .header("Cache_Control", "public, only-if-cached, max-stale = $maxStale")
                    .removeHeader("Pragma")
                    .build()
        }
        chain.proceed(request)
    }
}
