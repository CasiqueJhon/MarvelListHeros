package com.prueba.tecnica.marvellistheros.features.favorites.domain.usecase

import com.prueba.tecnica.marvellistheros.features.commons.domain.model.Favorite
import com.prueba.tecnica.marvellistheros.features.commons.domain.repository.FavoriteRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    suspend operator fun invoke(favorite: Favorite) {
        return favoriteRepository.delete(favorite)
    }
}