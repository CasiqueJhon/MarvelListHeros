package com.prueba.tecnica.marvellistheros.features.characters.domain.repository

import com.prueba.tecnica.marvellistheros.core.network.ResultWrapper
import com.prueba.tecnica.marvellistheros.features.characters.domain.model.Character

interface CharactersRepository {

    suspend fun getCharacters(
            apiKey: String,
            hash: String,
            ts: Long,
            offset: Int
    ): ResultWrapper<List<Character>>
}