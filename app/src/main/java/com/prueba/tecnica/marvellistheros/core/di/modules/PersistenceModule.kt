package com.prueba.tecnica.marvellistheros.core.di.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prueba.tecnica.marvellistheros.core.persistence.RoomDataBase
import com.prueba.tecnica.marvellistheros.features.commons.data.FavoriteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object PersistenceModule {

    @Provides
    @Singleton
    fun providesDataBase(context: Context): RoomDatabase =
        Room.databaseBuilder(context, RoomDatabase::class.java, "marvel-db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideFavoriteDao(appDataBase: RoomDataBase): FavoriteDao = appDataBase.favoriteDao()

}