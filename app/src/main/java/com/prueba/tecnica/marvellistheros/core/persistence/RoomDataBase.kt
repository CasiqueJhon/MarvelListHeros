package com.prueba.tecnica.marvellistheros.core.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prueba.tecnica.marvellistheros.features.commons.data.FavoriteDao

@Database(
    entities = [FavoriteDao::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDataBase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}