package com.crleo.pokedex.ui.features.pokedex.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.crleo.pokedex.ui.components.ErrorState
import com.crleo.pokedex.ui.components.LoadingState
import com.crleo.pokedex.ui.features.pokedex.presentation.PokedexPresenter
import com.crleo.pokedex.ui.features.pokedex.presentation.PokedexViewState

@Composable
fun PokedexScreen(presenter: PokedexPresenter) {
    val state by presenter.state.collectAsState()

    when (val currentState = state) {
        is PokedexViewState.Loading -> {
            LoadingState()
        }
        is PokedexViewState.Success -> {
            val pokemonList = currentState.pokemonTopLevelList
            PokemonList(
                pokemonList = pokemonList,
                isLoading = currentState.isLoading,
                isError = currentState.isError,
                onNextPage = presenter::getNextPage
            )
        }
        is PokedexViewState.Error -> {
            val errorMessage = currentState.errorMessage
            ErrorState(errorMessage = errorMessage)
        }
    }
}