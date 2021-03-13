package com.prueba.tecnica.marvellistheros.features.characters.domain.usecase

import com.prueba.tecnica.marvellistheros.core.network.ResultWrapper
import com.prueba.tecnica.marvellistheros.features.characters.domain.model.Character
import com.prueba.tecnica.marvellistheros.features.characters.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
        private val charactersRepository: CharactersRepository
) {

    suspend operator fun invoke(
            apiKey: String,
            hash: String,
            ts: Long,
            offset: Int
    ): ResultWrapper<List<Character>> {
        return charactersRepository.getCharacters(apiKey, hash, ts, offset)
    }
}