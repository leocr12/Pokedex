package com.crleo.pokedex.ui.features.pokedex.domain

import com.crleo.pokedex.data.model.PokemonTopLevel

interface PokedexInteractor {
    suspend fun getPokemonList(limit: Int, offset: Int): List<PokemonTopLevel>
}