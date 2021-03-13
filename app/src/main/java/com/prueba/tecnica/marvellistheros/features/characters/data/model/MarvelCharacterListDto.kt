package com.prueba.tecnica.marvellistheros.features.characters.data.model

import com.google.gson.annotations.SerializedName

data class MarvelCharacterListDto(
        @SerializedName("data")
        val data: CharacterListDto
)