package com.prueba.tecnica.marvellistheros.features.details.data

import com.prueba.tecnica.marvellistheros.features.details.data.model.MarvelCharacterDetailDto
import com.prueba.tecnica.marvellistheros.features.details.data.model.MarvelDetailInfoDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailsApi {

    @GET("characters/{charactersId}")
    suspend fun getDetails(
        @Path("characterId") characterId: Long,
        @Query("apiKey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Long
    ): MarvelCharacterDetailDto

    @GET("characters/{characterId}/comics")
    suspend fun getDetailsComics(
        @Path("characterId") characterId: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Long,
        @Query("offset") offset: Int
    ): MarvelDetailInfoDto

    @GET("characters/{characterId}/series")
    suspend fun getDetailsSeries(
        @Path("characterId") characterId: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Long,
        @Query("offset") offset: Int
    ): MarvelDetailInfoDto
}