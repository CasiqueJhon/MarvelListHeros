package com.prueba.tecnica.marvellistheros.core.plataform

import android.util.Log
import kotlin.properties.Delegates

object Config {

    private const val TAG = "NetworkConnectivity"
    const val URL_BASE_MARVEL = "https://gateway.marvel.com/v1/public/"
    const val API_KEY = "f539b40a6dffb1b51dad55757435fc94"
    const val PRIVATE_API_KEY = "162593b19e6809ce7244f9adb61c6cac8b86b0b4"

    var isNetworkConnected: Boolean by Delegates.observable(false) {_, _, newValue ->
        Log.i(TAG, "Status = $newValue")
    }

    var refreshCharacter: Boolean = false
    var refreshFavorite: Boolean = false
}