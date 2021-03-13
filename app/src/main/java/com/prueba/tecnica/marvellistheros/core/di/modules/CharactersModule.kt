package com.prueba.tecnica.marvellistheros.core.di.modules

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.prueba.tecnica.marvellistheros.core.di.key.FragmentKey
import com.prueba.tecnica.marvellistheros.core.di.key.ViewModelKey
import com.prueba.tecnica.marvellistheros.features.characters.data.CharactersDataSource
import com.prueba.tecnica.marvellistheros.features.characters.domain.repository.CharactersRepository
import com.prueba.tecnica.marvellistheros.features.characters.presentation.CharactersFragment
import com.prueba.tecnica.marvellistheros.features.characters.presentation.CharactersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CharactersModule {

    @Binds
    abstract fun characterRepository(charactersDataSource: CharactersDataSource): CharactersRepository

    @Binds
    @IntoMap
    @FragmentKey(CharactersFragment::class)
    abstract fun bindCharactersFragment(charactersFragment: CharactersFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    abstract fun bindCharactersViewModel(charactersViewModel: CharactersViewModel): ViewModel
}