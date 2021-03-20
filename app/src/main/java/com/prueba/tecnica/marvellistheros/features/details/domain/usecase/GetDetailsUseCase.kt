package com.prueba.tecnica.marvellistheros.features.details.domain.usecase

import com.prueba.tecnica.marvellistheros.core.di.key.ViewModelKey
import com.prueba.tecnica.marvellistheros.core.network.ResultWrapper
import com.prueba.tecnica.marvellistheros.features.details.domain.model.CharacterDetail
import com.prueba.tecnica.marvellistheros.features.details.domain.repository.DetailsRepository
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
        private val detailsRepository: DetailsRepository
) {

    suspend operator fun invoke(
            characterId: Long,
            apiKey: String,
            ts: Long,
            hash: String,

    ): ResultWrapper<CharacterDetail> {
        return detailsRepository.getDetails(characterId, apiKey, hash, ts)
    }

}