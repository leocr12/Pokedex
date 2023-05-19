package com.crleo.pokedex.ui.features.details.data

import com.crleo.pokedex.data.model.PokemonDetails

interface PokemonDetailsRepository {
    suspend fun getPokemonDetails(pokemonId: Int): PokemonDetails
}