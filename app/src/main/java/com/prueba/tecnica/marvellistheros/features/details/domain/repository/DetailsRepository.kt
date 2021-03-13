package com.prueba.tecnica.marvellistheros.features.details.domain.repository

import com.prueba.tecnica.marvellistheros.core.network.ResultWrapper
import com.prueba.tecnica.marvellistheros.features.details.domain.model.CharacterDetail
import com.prueba.tecnica.marvellistheros.features.details.domain.model.DetailInfo

interface DetailsRepository {

    suspend fun getDetails(
            characterId: Long,
            apiKey: String,
            hash: String,
            ts: Long
    ): ResultWrapper<CharacterDetail>

    suspend fun getDetailsComics(
            characterId: Long,
            apiKey: String,
            hash: String,
            ts: Long,
            offset: Int
    ): ResultWrapper<List<DetailInfo>>

    suspend fun getDetailsSeries(
            characterId: Long,
            apiKey: String,
            hash: String,
            ts: Long,
            offset: Int
    ): ResultWrapper<List<DetailInfo>>
}