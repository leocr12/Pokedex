package com.crleo.pokedex.ui.features.details.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.crleo.pokedex.data.model.PokemonDetails
import com.crleo.pokedex.ui.components.ErrorState
import com.crleo.pokedex.ui.components.LoadingState
import com.crleo.pokedex.ui.features.details.presentation.PokemonDetailsPresenter
import com.crleo.pokedex.ui.features.details.presentation.PokemonDetailsViewState

@Composable
fun PokemonDetailsScreen(pokemonId: Int, presenter: PokemonDetailsPresenter) {
    val state by presenter.state.collectAsState()

    LaunchedEffect(Unit) {
        presenter.getPokemonDetails(pokemonId)
    }

    when (val currentState = state) {
        is PokemonDetailsViewState.Loading -> {
            LoadingState()
        }
        is PokemonDetailsViewState.Success -> {
            val pokemonDetails = currentState.pokemonDetails
            PokemonInformation(pokemonDetails = pokemonDetails)
        }
        is PokemonDetailsViewState.Error -> {
            val errorMessage = currentState.errorMessage
            ErrorState(errorMessage = errorMessage)
        }
    }
}