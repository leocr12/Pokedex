package com.crleo.pokedex.data.model

data class Type(
    val type: InternalType
) {
    data class InternalType(
        val name: String
    )
}
