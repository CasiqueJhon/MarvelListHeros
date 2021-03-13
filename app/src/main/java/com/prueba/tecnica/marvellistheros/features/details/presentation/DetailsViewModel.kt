package com.prueba.tecnica.marvellistheros.features.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prueba.tecnica.marvellistheros.features.details.domain.model.CharacterDetail
import com.prueba.tecnica.marvellistheros.features.details.domain.usecase.GetDetailsComicsUseCase
import com.prueba.tecnica.marvellistheros.features.details.domain.usecase.GetDetailsSeriesUseCase
import com.prueba.tecnica.marvellistheros.features.details.domain.usecase.GetDetailsUseCase
import com.prueba.tecnica.marvellistheros.features.details.domain.usecase.SaveFavoriteUseCase
import javax.inject.Inject

open class DetailsViewModel @Inject constructor(
        private val getDetailsUseCase: GetDetailsUseCase,
        private val getDetailsComicsUseCase: GetDetailsComicsUseCase,
        private val getDetailsSeriesUseCase: GetDetailsSeriesUseCase,
        private val saveFavoriteUseCase: SaveFavoriteUseCase
): ViewModel() {
    open val details: LiveData<CharacterDetail?> get()= _details
    private val _details = MutableLiveData<CharacterDetail?>()

}