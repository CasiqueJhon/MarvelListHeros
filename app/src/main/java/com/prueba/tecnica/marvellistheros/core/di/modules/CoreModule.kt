package com.prueba.tecnica.marvellistheros.core.di.modules

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.prueba.tecnica.marvellistheros.core.network.CacheInterceptor
import com.prueba.tecnica.marvellistheros.features.characters.data.CharactersApi
import com.prueba.tecnica.marvellistheros.features.details.data.DetailsApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
abstract class CoreModule {

    companion object {

        @Provides
        fun gson(): Gson = Gson()

        @Provides
        fun Retrofit.charactersApi(): CharactersApi = create()

        @Provides
        fun Retrofit.detailsApi(): DetailsApi = create()

        @Provides
        fun cacheInterceptor(): CacheInterceptor = CacheInterceptor()
    }

    @Binds
    abstract fun Application.context(): Context
}