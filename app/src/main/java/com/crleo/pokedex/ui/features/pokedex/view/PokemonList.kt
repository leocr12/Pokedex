package com.crleo.pokedex.ui.features.pokedex.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.crleo.pokedex.data.model.PokemonTopLevel
import com.crleo.pokedex.ui.components.ErrorState
import com.crleo.pokedex.ui.components.LoadingState

@Composable
fun PokemonList(
    pokemonList: List<PokemonTopLevel>,
    isLoading: Boolean,
    isError: Boolean,
    onNextPage: () -> Unit
) {
    LazyColumn {
        itemsIndexed(items = pokemonList) { index, pokemon ->
            PokemonItem(pokemon = pokemon, index = index + 1)
            if (index == pokemonList.size - 1 && !isLoading && !isError) {
                onNextPage()
            }
        }
    }

    if (isLoading) {
        LoadingState()
    }

    if (isError) {
        ErrorState(errorMessage = "Error loading Pokemon list")
    }
}