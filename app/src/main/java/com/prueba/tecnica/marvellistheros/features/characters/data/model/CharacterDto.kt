package com.prueba.tecnica.marvellistheros.features.characters.data.model

import com.google.gson.annotations.SerializedName
import com.prueba.tecnica.marvellistheros.core.TwoWayMapper
import com.prueba.tecnica.marvellistheros.features.characters.domain.model.Character
import javax.inject.Inject

data class CharacterDto(
        @SerializedName("id")
        val id: Long?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("thumbnail")
        val thumbnail: ThumbnailDto
)

class CharacterDtoMapper @Inject constructor(): TwoWayMapper<CharacterDto, Character> {

        override fun map(param: CharacterDto): Character = with(param){
                Character(
                        id = id ?: 0,
                        name = name.orEmpty(),
                        thumbnail = thumbnail.let(ThumbnailDtoMapper()::map)
                )
        }

        override fun mapReverse(param: Character): CharacterDto = with(param) {
                CharacterDto(
                        id = id,
                        name = name,
                        thumbnail = thumbnail.let(ThumbnailDtoMapper()::mapReverse)
                )
        }

}