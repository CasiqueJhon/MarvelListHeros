package com.prueba.tecnica.marvellistheros.features.commons.data

import androidx.room.*
import com.prueba.tecnica.marvellistheros.features.commons.data.model.FavoriteDto

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    fun getAll(): List<FavoriteDto>

    @Query("SELECT * FROM favorite WHERE favoriteId = :favoriteId")
    fun get(favoriteId: Long): FavoriteDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(goalDataModel: FavoriteDto): Long

    @Delete
    fun delete(goalDataModel: FavoriteDto)

}