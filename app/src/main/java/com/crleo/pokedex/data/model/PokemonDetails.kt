package com.crleo.pokedex.data.model

data class PokemonDetails(
    val id: String,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val types: List<Type>
)
