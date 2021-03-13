package com.prueba.tecnica.marvellistheros.features.characters.domain.usecase

import com.prueba.tecnica.marvellistheros.features.commons.domain.model.Favorite
import com.prueba.tecnica.marvellistheros.features.commons.domain.repository.FavoriteRepository
import javax.inject.Inject

class SaveFavoriteUseCase @Inject constructor(
        private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(
            favorite: Favorite
    ): Long {
        return favoriteRepository.save(favorite)
    }
}
