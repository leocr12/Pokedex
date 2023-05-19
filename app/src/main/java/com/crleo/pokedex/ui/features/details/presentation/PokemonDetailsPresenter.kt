package com.crleo.pokedex.ui.features.details.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface PokemonDetailsPresenter {
    val state: StateFlow<PokemonDetailsViewState>

    fun setScope(scope: CoroutineScope)

    fun getPokemonDetails(pokemonId: Int)
}