package com.prueba.tecnica.marvellistheros.features.commons.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prueba.tecnica.marvellistheros.core.TwoWayMapper
import com.prueba.tecnica.marvellistheros.features.commons.domain.model.Favorite
import java.io.Serializable
import javax.inject.Inject

@Entity(tableName = "favorite")
data class FavoriteDto(
    @PrimaryKey(autoGenerate = true)
    val favoriteId: Long,
    val favoriteName: String,
    val favoriteUrl: String
): Serializable

class FavoriteDtoMapper @Inject constructor(): TwoWayMapper<FavoriteDto, Favorite> {

    override fun map(param: FavoriteDto): Favorite = with(param) {
        Favorite(
            favoriteId = favoriteId,
            favoriteName = favoriteName,
            favoriteUrl = favoriteUrl
        )
    }

    override fun mapReverse(param: Favorite): FavoriteDto = with(param) {
        FavoriteDto(
            favoriteId = favoriteId,
            favoriteName = favoriteName,
            favoriteUrl = favoriteUrl
        )
    }
}