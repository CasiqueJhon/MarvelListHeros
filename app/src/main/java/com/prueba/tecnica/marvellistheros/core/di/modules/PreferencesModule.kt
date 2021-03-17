package com.prueba.tecnica.marvellistheros.core.di.modules

import android.content.Context
import com.prueba.tecnica.marvellistheros.core.persistence.sharedpreferences.Preferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object PreferencesModule {

    @Provides
    fun provideSharedPreferences(context: Context): Preferences = Preferences(context)

}