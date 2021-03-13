package com.prueba.tecnica.marvellistheros.features.characters.data.model

import com.google.gson.annotations.SerializedName

data class CharacterDto(
        @SerializedName("id")
        val id: Long?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("thumbnail")
        val thumbnail: ThumbnailDto
)