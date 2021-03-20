package com.prueba.tecnica.marvellistheros

import android.app.Application
import com.prueba.tecnica.marvellistheros.core.di.AppComponent
import com.prueba.tecnica.marvellistheros.core.di.AppComponentProvider
import com.prueba.tecnica.marvellistheros.core.di.DaggerAppComponent
import com.prueba.tecnica.marvellistheros.core.network.NetworkMonitor

class App: Application(), AppComponentProvider {

    override val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory()
            .create(application = this)
    }

    override fun onCreate() {
        super.onCreate()

        NetworkMonitor(this).startNetworkCallback()
    }

}