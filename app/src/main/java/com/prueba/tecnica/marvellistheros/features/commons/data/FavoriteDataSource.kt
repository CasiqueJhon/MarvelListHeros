package com.prueba.tecnica.marvellistheros.features.commons.data

import androidx.lifecycle.Transformations.map
import com.prueba.tecnica.marvellistheros.features.commons.data.model.FavoriteDtoMapper
import com.prueba.tecnica.marvellistheros.features.commons.domain.model.Favorite
import com.prueba.tecnica.marvellistheros.features.commons.domain.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteDataSource @Inject constructor(
    private val favoriteDao: FavoriteDao,
    private val favoriteDtoMapper: FavoriteDtoMapper
): FavoriteRepository {

    override suspend fun getFavorites(): List<Favorite> {
        return withContext(Dispatchers.IO) {
            favoriteDao.getAll()
                .map { favoriteDtoMapper.map(it) }
        }
    }

    override suspend fun getFavorite(favoriteId: Long): Favorite {
        return withContext(Dispatchers.IO) {
            favoriteDao.get(favoriteId).let {
                favoriteDtoMapper.map(it)
            }
        }
    }

    override suspend fun save(favorite: Favorite): Long {
       return withContext(Dispatchers.IO) {
           favoriteDao.save(favorite.let {
               favoriteDtoMapper.mapReverse(it)
           })
       }
    }

    override suspend fun delete(favorite: Favorite) {
        return withContext(Dispatchers.IO) {
            favoriteDao.delete(favorite.let {
                favoriteDtoMapper.mapReverse(it)
            })
        }
    }


}