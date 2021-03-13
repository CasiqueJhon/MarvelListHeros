package com.prueba.tecnica.marvellistheros.features.characters.data.model

import com.google.gson.annotations.SerializedName

data class ThumbnailDto(
        @SerializedName("path")
        val path: String?,
        @SerializedName("extension")
        val extension: String?
)