package com.prueba.tecnica.marvellistheros.core

interface Mapper<IntType, OutType> {
    fun map(param: IntType): OutType

    fun mapList(param: List<IntType>): List<OutType> = param.map(::map)
}

interface TwoWayMapper<IntType, OutType>: Mapper<IntType, OutType> {
    fun mapReverse(param: OutType): IntType

    fun mapListReverse(param: List<OutType>): List<IntType> = param.map(::mapReverse)
}