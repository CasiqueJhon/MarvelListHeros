package com.prueba.tecnica.marvellistheros.core.di.modules

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.prueba.tecnica.marvellistheros.core.di.key.FragmentKey
import com.prueba.tecnica.marvellistheros.core.di.key.ViewModelKey
import com.prueba.tecnica.marvellistheros.features.favorites.presentation.FavoriteFragment
import com.prueba.tecnica.marvellistheros.features.favorites.presentation.FavoriteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavoriteModule {

    @Binds
    @IntoMap
    @FragmentKey(FavoriteFragment::class)
    abstract fun bindFavoriteFragment(favoriteFragment: FavoriteFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(favoriteViewModel: FavoriteViewModel): ViewModel


}