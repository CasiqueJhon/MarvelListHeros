package com.prueba.tecnica.marvellistheros.features.favorites.domain.usecase

import com.prueba.tecnica.marvellistheros.features.commons.domain.repository.ListModeRepository
import javax.inject.Inject

class SaveLisModeUseCase @Inject constructor(
    private val listModeRepository: ListModeRepository

)  {

    suspend operator fun invoke(listMode: Boolean) {
        return listModeRepository.saveListMode(listMode)
    }
}