package com.crleo.pokedex.ui.features.details.presentation

import com.crleo.pokedex.data.model.PokemonDetails

sealed class PokemonDetailsViewState {
    object Loading: PokemonDetailsViewState()
    data class Success(val pokemonDetails: PokemonDetails): PokemonDetailsViewState()
    data class Error(val errorMessage: String): PokemonDetailsViewState()
}
