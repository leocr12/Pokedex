package com.crleo.pokedex.ui.features.pokedex.presentation

import com.crleo.pokedex.data.model.PokemonTopLevel

sealed class PokedexViewState {
    object Loading: PokedexViewState()
    data class Success(
        val pokemonTopLevelList: List<PokemonTopLevel>,
        val isLoading: Boolean,
        val isError: Boolean
    ): PokedexViewState()
    data class Error(val errorMessage: String): PokedexViewState()
}