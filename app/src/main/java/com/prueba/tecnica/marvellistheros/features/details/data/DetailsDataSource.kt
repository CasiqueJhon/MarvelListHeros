package com.prueba.tecnica.marvellistheros.features.details.data

import com.prueba.tecnica.marvellistheros.core.network.Network
import com.prueba.tecnica.marvellistheros.core.network.ResultWrapper
import com.prueba.tecnica.marvellistheros.features.details.data.model.CharacterDetailDtoMapper
import com.prueba.tecnica.marvellistheros.features.details.data.model.DetailInfoDtoMapper
import com.prueba.tecnica.marvellistheros.features.details.domain.model.CharacterDetail
import com.prueba.tecnica.marvellistheros.features.details.domain.model.DetailInfo
import com.prueba.tecnica.marvellistheros.features.details.domain.repository.DetailsRepository
import javax.inject.Inject

class DetailsDataSource @Inject constructor(
    private val detailsApi: DetailsApi,
    private val characterDetailDtoMapper: CharacterDetailDtoMapper,
    private val detailInfoDtoMapper: DetailInfoDtoMapper
): DetailsRepository {

    override suspend fun getDetails(
        characterId: Long,
        apiKey: String,
        hash: String,
        ts: Long
    ): ResultWrapper<CharacterDetail> {
        return Network.request() {
            characterDetailDtoMapper.map(
                detailsApi.getDetails(
                    characterId,
                    apiKey,
                    hash,
                    ts
                ).data.items.first()
            )
        }
    }

    override suspend fun getDetailsComics(
        characterId: Long,
        apiKey: String,
        hash: String,
        ts: Long,
        offset: Int
    ): ResultWrapper<List<DetailInfo>> {
        return Network.request() {
            detailsApi.getDetailsComics(characterId, apiKey, hash, ts, offset).data.items
                .map { detailInfoDtoMapper.map(it) }
        }
    }

    override suspend fun getDetailsSeries(
        characterId: Long,
        apiKey: String,
        hash: String,
        ts: Long,
        offset: Int
    ): ResultWrapper<List<DetailInfo>> {
        return Network.request() {
            detailsApi.getDetailsSeries(characterId, apiKey, hash, ts, offset).data.items
                .map { detailInfoDtoMapper.map(it) }
        }
    }
}