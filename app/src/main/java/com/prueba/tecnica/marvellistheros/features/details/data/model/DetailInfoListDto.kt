package com.prueba.tecnica.marvellistheros.features.details.data.model

import com.google.gson.annotations.SerializedName
import com.prueba.tecnica.marvellistheros.core.TwoWayMapper
import com.prueba.tecnica.marvellistheros.features.details.domain.model.DetailInfoList
import javax.inject.Inject

data class DetailInfoListDto(
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("results")
    val items: List<DetailInfoDto>
)

class DetailInfoListDtoMapper @Inject constructor() :
        TwoWayMapper<DetailInfoListDto, DetailInfoList> {
    override fun map(param: DetailInfoListDto): DetailInfoList = with(param) {
        DetailInfoList(
            offset = offset ?: 0,
            limit = limit ?: 0,
            total = total ?: 0,
            count = count ?: 0,
            results = DetailInfoDtoMapper().mapList(items)
        )
    }

    override fun mapReverse(param: DetailInfoList): DetailInfoListDto = with(param){
        DetailInfoListDto(
            offset = offset,
            limit = limit,
            total = total,
            count = count,
            items = DetailInfoDtoMapper().mapListReverse(results)
        )
    }

}