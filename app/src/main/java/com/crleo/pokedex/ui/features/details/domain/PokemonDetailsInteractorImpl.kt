package com.crleo.pokedex.ui.features.details.domain

import com.crleo.pokedex.data.model.PokemonDetails
import com.crleo.pokedex.ui.features.details.data.PokemonDetailsRepository
import javax.inject.Inject

class PokemonDetailsInteractorImpl @Inject constructor(private val repository: PokemonDetailsRepository): PokemonDetailsInteractor {
    override suspend fun getPokemonDetails(pokemonId: Int): PokemonDetails {
        return repository.getPokemonDetails(pokemonId)
    }

}