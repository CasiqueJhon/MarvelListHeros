package com.prueba.tecnica.marvellistheros.features.favorites.presentation

import androidx.lifecycle.ViewModel
import com.prueba.tecnica.marvellistheros.features.favorites.domain.usecase.DeleteFavoriteUseCase
import com.prueba.tecnica.marvellistheros.features.favorites.domain.usecase.GetFavoritesUseCase
import com.prueba.tecnica.marvellistheros.features.favorites.domain.usecase.GetListModeUseCase
import com.prueba.tecnica.marvellistheros.features.favorites.domain.usecase.SaveLisModeUseCase
import javax.inject.Inject

open class FavoriteViewModel @Inject constructor(
        private val getFavoritesUseCase: GetFavoritesUseCase,
        private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
        private val saveListModeUseCase: SaveLisModeUseCase,
        private val getLisModeUseCase: GetListModeUseCase
): ViewModel() {
}