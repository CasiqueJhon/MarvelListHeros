package com.prueba.tecnica.marvellistheros.features.details.data.model

import com.google.gson.annotations.SerializedName
import com.prueba.tecnica.marvellistheros.core.TwoWayMapper
import com.prueba.tecnica.marvellistheros.features.details.domain.model.CharacterDetailList
import javax.inject.Inject

data class CharacterDetailListDto(
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("results")
    val items: List<CharacterDetailDto>
)

class CharacterDetailListDtoMapper @Inject constructor():
        TwoWayMapper<CharacterDetailListDto, CharacterDetailList> {
    override fun map(param: CharacterDetailListDto): CharacterDetailList = with(param) {
        CharacterDetailList(
            offset = offset ?: 0,
            limit = limit ?: 0,
            total = total ?:0,
            count = count ?: 0,
            results = CharacterDetailDtoMapper().mapList(items)
        )
    }

    override fun mapReverse(param: CharacterDetailList): CharacterDetailListDto = with(param) {
        CharacterDetailListDto(
            offset = offset,
            limit = limit,
            total = total,
            count = count,
            items = CharacterDetailDtoMapper().mapListReverse(results)
        )
    }
}