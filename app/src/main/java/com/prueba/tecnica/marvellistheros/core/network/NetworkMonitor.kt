package com.prueba.tecnica.marvellistheros.core.network

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.prueba.tecnica.marvellistheros.core.plataform.Config.isNetworkConnected
import javax.inject.Inject

class NetworkMonitor @Inject constructor(
    private val application: Application
) {

    fun startNetworkCallback() {
        val connectivityManager: ConnectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()

        connectivityManager.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    isNetworkConnected = true
                }

                override fun onLost(network: Network) {
                    isNetworkConnected = false
                }
            })
    }

    fun stopNetworkCallback() {
        val connectivityManager: ConnectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(ConnectivityManager.NetworkCallback())
    }
}