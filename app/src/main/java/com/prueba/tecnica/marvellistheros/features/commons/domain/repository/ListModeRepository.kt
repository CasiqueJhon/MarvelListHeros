package com.prueba.tecnica.marvellistheros.features.commons.domain.repository

interface ListModeRepository {

    suspend fun saveListMode(listMode: Boolean)

    suspend fun getListMode(): Boolean
}