package com.prueba.tecnica.marvellistheros.features.characters.domain.model

import com.prueba.tecnica.marvellistheros.core.extension.convertToHttps

data class Thumbnail(
    val path: String,
    val extension: String
) {
    fun getUrl() = "$path/landscape_incredible.$extension".convertToHttps()
}