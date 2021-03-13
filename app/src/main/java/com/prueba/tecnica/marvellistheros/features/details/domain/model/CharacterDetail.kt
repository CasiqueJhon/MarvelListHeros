package com.prueba.tecnica.marvellistheros.features.details.domain.model

data class CharacterDetail(
        val id: Long,
        val name: String,
        val description: String,
        val thumbnail: Thumbnail
)
