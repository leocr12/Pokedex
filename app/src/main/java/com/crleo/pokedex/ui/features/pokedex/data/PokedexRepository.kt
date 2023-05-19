package com.crleo.pokedex.ui.features.pokedex.data

import com.crleo.pokedex.data.model.PokemonTopLevel

interface PokedexRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): List<PokemonTopLevel>
}