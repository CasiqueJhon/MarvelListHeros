package com.prueba.tecnica.marvellistheros.core.di.provider

import com.prueba.tecnica.marvellistheros.features.characters.presentation.CharactersViewModel
import com.prueba.tecnica.marvellistheros.features.details.presentation.DetailsViewModel
import com.prueba.tecnica.marvellistheros.features.favorites.presentation.FavoriteViewModel

interface ViewModelProvider {

    fun charactersViewModel(): CharactersViewModel

    fun detailsViewModel(): DetailsViewModel

    fun favoriteViewModel(): FavoriteViewModel
}