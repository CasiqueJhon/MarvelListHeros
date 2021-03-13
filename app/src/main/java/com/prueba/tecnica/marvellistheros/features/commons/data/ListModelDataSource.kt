package com.prueba.tecnica.marvellistheros.features.commons.data

import com.prueba.tecnica.marvellistheros.core.persistence.sharedpreferences.Preferences
import com.prueba.tecnica.marvellistheros.features.commons.domain.repository.ListModeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListModelDataSource @Inject constructor(
    private val preferences: Preferences
): ListModeRepository {

    override suspend fun saveListMode(listMode: Boolean) {
        return withContext(Dispatchers.IO) {
            preferences.listMode = listMode
        }
    }

    override suspend fun getListMode(): Boolean {
        return withContext(Dispatchers.IO) {
            preferences.listMode
        }
    }
}