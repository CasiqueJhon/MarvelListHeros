package com.prueba.tecnica.marvellistheros.core.di.modules

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.prueba.tecnica.marvellistheros.core.network.CACHE_MAX_AGE
import com.prueba.tecnica.marvellistheros.core.network.NetworkMonitor
import com.prueba.tecnica.marvellistheros.core.plataform.Config
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val CACHE_MAX_SIZE = (10 * 1024 * 1024).toLong()

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(Config.URL_BASE_MARVEL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun okHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    fun cache(
        context: Context
    ): Cache = Cache(context.cacheDir, CACHE_MAX_SIZE)

    @Provides
    fun networkMonitor(
        application: Application
    ): NetworkMonitor = NetworkMonitor(application)
}