package com.prueba.tecnica.marvellistheros.features.characters.data

import com.prueba.tecnica.marvellistheros.core.network.Network
import com.prueba.tecnica.marvellistheros.core.network.ResultWrapper
import com.prueba.tecnica.marvellistheros.features.characters.data.model.CharacterDtoMapper
import com.prueba.tecnica.marvellistheros.features.characters.domain.model.Character
import com.prueba.tecnica.marvellistheros.features.characters.domain.repository.CharactersRepository
import com.prueba.tecnica.marvellistheros.features.details.data.model.CharacterDetailDtoMapper
import javax.inject.Inject

class CharactersDataSource @Inject constructor(
    private val charactersApi: CharactersApi,
    private val characterDtoMapper: CharacterDtoMapper
) : CharactersRepository {

    override suspend fun getCharacters(
        apiKey: String,
        hash: String,
        ts: Long,
        offset: Int
    ): ResultWrapper<List<Character>> {
        return Network.request {
            charactersApi.getAllCharacters(apiKey, hash, ts, offset).data.items
                .map { characterDtoMapper.map(it) }
        }
    }
}