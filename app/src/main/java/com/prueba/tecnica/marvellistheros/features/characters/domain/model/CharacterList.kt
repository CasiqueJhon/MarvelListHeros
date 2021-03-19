package com.prueba.tecnica.marvellistheros.features.characters.domain.model

data class CharacterList(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val items: List<Character>
)