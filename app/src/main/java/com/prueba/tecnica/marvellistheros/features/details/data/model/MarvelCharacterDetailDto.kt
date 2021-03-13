package com.prueba.tecnica.marvellistheros.features.details.data.model

import com.google.gson.annotations.SerializedName
import com.prueba.tecnica.marvellistheros.core.TwoWayMapper
import javax.inject.Inject

data class MarvelCharacterDetailDto(
    @SerializedName("data")
    val data: CharacterDetailListDto
)

class MarvelCharacterDetailDtoMapper @Inject constructor():
    TwoWayMapper<MarvelCharacterDetailDto, MarvelCharacterDetail> {
    override fun map(param: MarvelCharacterDetailDto): MarvelCharacterDetail = with(param) {
        MarvelCharacterDetail(
            data = data.let(CharacterDetailListDtoMapper()::map)
        )
    }

    override fun mapReverse(param: MarvelCharacterDetail): MarvelCharacterDetailDto = with(param) {
        MarvelCharacterDetailDto(
            data = data.let(CharacterDetailListDtoMapper()::mapReverse)
        )
    }
}