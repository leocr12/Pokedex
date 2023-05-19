package com.crleo.pokedex.ui.features.pokedex.domain

import com.crleo.pokedex.data.model.PokemonDetails
import com.crleo.pokedex.data.model.PokemonTopLevel
import com.crleo.pokedex.ui.features.pokedex.data.PokedexRepository
import javax.inject.Inject

class PokedexInteractorImpl @Inject constructor(private val repository: PokedexRepository): PokedexInteractor {
    override suspend fun getPokemonList(limit: Int, offset: Int): List<PokemonTopLevel> {
        return repository.getPokemonList(limit, offset)
    }
}