package com.prueba.tecnica.marvellistheros.features.commons.data

import androidx.room.Dao
import androidx.room.Query
import com.prueba.tecnica.marvellistheros.features.commons.data.model.FavoriteDto

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    fun getAll(): List<FavoriteDto>

}