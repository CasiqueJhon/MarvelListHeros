package com.prueba.tecnica.marvellistheros.features.characters.presentation

import androidx.lifecycle.ViewModel
import com.prueba.tecnica.marvellistheros.features.characters.domain.usecase.GetCharactersUseCase
import com.prueba.tecnica.marvellistheros.features.characters.domain.usecase.GetListModeUseCase
import com.prueba.tecnica.marvellistheros.features.characters.domain.usecase.SaveFavoriteUseCase
import com.prueba.tecnica.marvellistheros.features.characters.domain.usecase.SaveListModeUseCase
import javax.inject.Inject

open class CharactersViewModel @Inject constructor(
        private val getCharactersUseCase: GetCharactersUseCase,
        private val saveFavoriteUseCase: SaveFavoriteUseCase,
        private val saveListModeUseCase: SaveListModeUseCase,
        private val getListModeUseCase: GetListModeUseCase
): ViewModel() {
}