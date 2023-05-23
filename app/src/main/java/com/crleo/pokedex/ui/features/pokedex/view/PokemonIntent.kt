package com.crleo.pokedex.ui.features.pokedex.view

sealed class PokemonIntent {
    data class PokemonClicked(val index: Int): PokemonIntent()
}
