package com.prueba.tecnica.marvellistheros.features.characters.data.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class CharacterListDto(
        @SerializedName("offset")
        val offset: Int?,
        @SerializedName("limit")
        val limit: Int?,
        @SerializedName("total")
        val total: Int?,
        @SerializedName("count")
        val count: Int?,
        @SerializedName("results")
        val items: List<CharacterDto>
)
