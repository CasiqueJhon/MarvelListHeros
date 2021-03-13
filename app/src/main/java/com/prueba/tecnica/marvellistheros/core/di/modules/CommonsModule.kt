package com.prueba.tecnica.marvellistheros.core.di.modules

import com.prueba.tecnica.marvellistheros.features.commons.data.FavoriteDataSource
import com.prueba.tecnica.marvellistheros.features.commons.data.ListModelDataSource
import com.prueba.tecnica.marvellistheros.features.commons.domain.repository.FavoriteRepository
import com.prueba.tecnica.marvellistheros.features.commons.domain.repository.ListModeRepository
import dagger.Binds
import dagger.Module

@Module
abstract class CommonsModule {

    @Binds
    abstract fun favoriteRepository(favoriteDataSource: FavoriteDataSource): FavoriteRepository

    @Binds
    abstract fun listModeRepository(listModeDataSource: ListModelDataSource): ListModeRepository

}