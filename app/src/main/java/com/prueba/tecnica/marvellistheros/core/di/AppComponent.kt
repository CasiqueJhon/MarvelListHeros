package com.prueba.tecnica.marvellistheros.core.di

import android.app.Application
import com.prueba.tecnica.marvellistheros.core.di.modules.*
import com.prueba.tecnica.marvellistheros.core.di.provider.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
        modules = [
                CharactersModule::class,
        DetailsModule::class,
        FavoriteModule::class,
        NetworkModule::class,
        CommonsModule::class,
        CoreModule::class,
        PersistenceModule::class,
        PreferencesModule::class
        ]
)
@Singleton
interface AppComponent : ViewModelProvider {
        @Component.Factory
        interface Factory {
                fun create(
                        @BindsInstance application: Application
                ): AppComponent
        }
}

interface AppComponentProvider {
        val appComponent: AppComponent
}