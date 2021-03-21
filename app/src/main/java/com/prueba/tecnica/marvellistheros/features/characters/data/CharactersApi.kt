package com.prueba.tecnica.marvellistheros.features.characters.data

import com.prueba.tecnica.marvellistheros.features.characters.data.model.MarvelCharacterListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {

    @GET("characters")
    suspend fun getAllCharacters(
            @Query("apikey") apikey: String,
            @Query("hash") hash: String,
            @Query("ts") ts: Long,
            @Query("offset") offset: Int
    ): MarvelCharacterListDto
}