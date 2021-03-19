package com.prueba.tecnica.marvellistheros.features.characters.domain.usecase

import com.prueba.tecnica.marvellistheros.core.network.ResultWrapper
import com.prueba.tecnica.marvellistheros.features.characters.domain.model.Character
import com.prueba.tecnica.marvellistheros.features.characters.domain.repository.CharactersRepository
import com.prueba.tecnica.marvellistheros.features.commons.domain.repository.ListModeRepository
import javax.inject.Inject

class SaveListModeUseCase @Inject constructor(
        private val listModeRepository: ListModeRepository
) {
    suspend operator fun invoke(listMode: Boolean) {
        return listModeRepository.saveListMode(listMode)
    }
}