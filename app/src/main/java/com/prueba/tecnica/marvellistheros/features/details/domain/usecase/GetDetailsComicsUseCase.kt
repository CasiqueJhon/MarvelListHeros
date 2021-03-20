package com.prueba.tecnica.marvellistheros.features.details.domain.usecase

import com.prueba.tecnica.marvellistheros.core.network.ResultWrapper
import com.prueba.tecnica.marvellistheros.features.details.domain.model.DetailInfo
import com.prueba.tecnica.marvellistheros.features.details.domain.repository.DetailsRepository
import javax.inject.Inject

class GetDetailsComicsUseCase @Inject constructor(

    private val detailsRepository: DetailsRepository

) {
    suspend operator fun invoke(
        characterId: Long,
        apiKey: String,
        ts: Long,
        hash: String,
        offset: Int
    ): ResultWrapper<List<DetailInfo>> {
        return detailsRepository.getDetailsComics(characterId, apiKey, hash, ts, offset)
    }
}