package com.crleo.pokedex.ui.features.details.domain

import com.crleo.pokedex.data.model.PokemonDetails

interface PokemonDetailsInteractor {
    suspend fun getPokemonDetails(pokemonId: Int): PokemonDetails
}