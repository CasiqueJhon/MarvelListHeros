package com.prueba.tecnica.marvellistheros.features.details.data.model

import com.google.gson.annotations.SerializedName
import com.prueba.tecnica.marvellistheros.core.TwoWayMapper
import com.prueba.tecnica.marvellistheros.features.details.domain.model.CharacterDetail
import javax.inject.Inject

data class CharacterDetailDto(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailDto
)

class CharacterDetailDtoMapper @Inject constructor():
        TwoWayMapper<CharacterDetailDto, CharacterDetail> {
    override fun map(param: CharacterDetailDto): CharacterDetail = with(param ){
        CharacterDetail(
            id = id ?: 0L,
            name = name.orEmpty(),
            description = description.orEmpty(),
            thumbnail = thumbnail.let (ThumbnailDtoMapper()::map)
        )
    }

    override fun mapReverse(param: CharacterDetail): CharacterDetailDto = with(param) {
        CharacterDetailDto(
            id = id,
            name = name,
            description = description,
            thumbnail = thumbnail.let(ThumbnailDtoMapper()::mapReverse)
        )
    }
}