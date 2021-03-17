package com.prueba.tecnica.marvellistheros.features.details.data

import com.prueba.tecnica.marvellistheros.core.network.ResultWrapper
import com.prueba.tecnica.marvellistheros.features.details.data.model.CharacterDetailDtoMapper
import com.prueba.tecnica.marvellistheros.features.details.domain.model.CharacterDetail
import com.prueba.tecnica.marvellistheros.features.details.domain.repository.DetailsRepository
import javax.inject.Inject

class DetailsDataSource @Inject constructor(
    private val detailsApi: DetailsApi,
    private val characterDetailDtoMapper: CharacterDetailDtoMapper,
    private val detailInfoDtoMapper: com.prueba.tecnica.marvellistheros.features.details.data.model.DetailInfoDto
): DetailsRepository {

    override suspend fun getDetails(
        characterId: Long,
        apiKey: String,
        hash: String,
        ts: Long
    ): ResultWrapper<CharacterDetail> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailsComics(
        characterId: Long,
        apiKey: String,
        hash: String,
        ts: Long,
        offset: Int
    ): ResultWrapper<List<com.prueba.tecnica.marvellistheros.features.details.domain.model.DetailInfo>> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailsSeries(
        characterId: Long,
        apiKey: String,
        hash: String,
        ts: Long,
        offset: Int
    ): ResultWrapper<List<com.prueba.tecnica.marvellistheros.features.details.domain.model.DetailInfo>> {
        TODO("Not yet implemented")
    }
}