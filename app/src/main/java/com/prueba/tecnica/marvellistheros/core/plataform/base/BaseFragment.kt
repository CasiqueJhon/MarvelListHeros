package com.prueba.tecnica.marvellistheros.core.plataform.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.prueba.tecnica.marvellistheros.core.di.AppComponent
import com.prueba.tecnica.marvellistheros.core.di.AppComponentProvider
import com.prueba.tecnica.marvellistheros.core.di.provider.ViewModelProvider
import com.prueba.tecnica.marvellistheros.core.extension.md5
import com.prueba.tecnica.marvellistheros.core.plataform.Config
import com.prueba.tecnica.marvellistheros.features.main.MainActivity
import java.util.*

open class BaseFragment: Fragment() {

    private val main: MainActivity get() = (activity as MainActivity)
    val navController: NavController? get() = main.navController

    private val appComponent: AppComponent
        get() = (activity?.application as AppComponentProvider).appComponent
    val viewModelProvider: ViewModelProvider
        get() = appComponent

    fun hideBackArrow() {
        if (activity is MainActivity) {
            (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    fun showBackArrow() {
        if (activity is MainActivity) {
            (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    fun setTitle(title: String?) {
        if (activity is MainActivity)
            (activity as MainActivity).supportActionBar?.title = title ?: ""
    }

    fun createTimestamp() = Date().time
    fun createHash(timestamp: Long) = (timestamp.toString() + Config.PRIVATE_API_KEY + Config.API_KEY).md5()
}