package com.prueba.tecnica.marvellistheros.features.favorites.domain.usecase

import com.prueba.tecnica.marvellistheros.features.commons.domain.model.Favorite
import com.prueba.tecnica.marvellistheros.features.commons.domain.repository.FavoriteRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository

) {

    suspend operator fun invoke(): List<Favorite> {
        return favoriteRepository.getFavorites().sortedBy { it.favoriteName }
    }
}