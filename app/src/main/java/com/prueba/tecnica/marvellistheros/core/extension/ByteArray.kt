package com.prueba.tecnica.marvellistheros.core.extension

fun ByteArray.toHex() = joinToString("") { "%02x".format(it)}