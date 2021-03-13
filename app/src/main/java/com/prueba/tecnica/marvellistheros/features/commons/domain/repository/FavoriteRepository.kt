package com.prueba.tecnica.marvellistheros.features.commons.domain.repository

import com.prueba.tecnica.marvellistheros.features.commons.domain.model.Favorite

interface FavoriteRepository {
    suspend fun getFavorites(): List<Favorite>

    suspend fun getFavorite(favoriteId: Long): Favorite

    suspend fun save(favorite: Favorite): Long

    suspend fun delete(favorite: Favorite)
}