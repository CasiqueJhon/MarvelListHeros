package com.prueba.tecnica.marvellistheros.features.details.data

import com.prueba.tecnica.marvellistheros.features.details.data.model.MarvelCharacterDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailsApi {

    @GET("characters/{charactersId}")
    suspend fun getDetails(
        @Path("characterId") characterId: Long,
        @Query("apiKey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Long
    ): MarvelCharacterDetailDto
}