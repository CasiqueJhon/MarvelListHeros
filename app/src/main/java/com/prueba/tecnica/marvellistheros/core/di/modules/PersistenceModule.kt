package com.prueba.tecnica.marvellistheros.core.di.modules

import android.content.Context
import androidx.room.Room
import com.prueba.tecnica.marvellistheros.core.persistence.RoomDatabase
import com.prueba.tecnica.marvellistheros.features.commons.data.FavoriteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object PersistenceModule {

    @Provides
    @Singleton
    fun providesDatabase(context: Context): RoomDatabase =
        Room.databaseBuilder(context, RoomDatabase::class.java, "marvel-db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideFavoriteDao(appDatabase: RoomDatabase): FavoriteDao = appDatabase.favoriteDao()
}