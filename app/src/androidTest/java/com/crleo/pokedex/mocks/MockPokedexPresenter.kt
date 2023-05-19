package com.crleo.pokedex.mocks

import com.crleo.pokedex.data.model.PokemonTopLevel
import com.crleo.pokedex.ui.features.pokedex.presentation.PokedexPresenter
import com.crleo.pokedex.ui.features.pokedex.presentation.PokedexViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MockPokedexPresenter: PokedexPresenter {

    private val stateFlow: MutableStateFlow<PokedexViewState> = MutableStateFlow(PokedexViewState.Loading)
    override val state: StateFlow<PokedexViewState>
        get() = stateFlow

    override fun setScope(scope: CoroutineScope) {

    }

    override fun getPokemonList() {
        val fakePokemonList = listOf(
            PokemonTopLevel("Bulbasaur", "https://pokeapi.co/api/v2/pokemon/1"),
            PokemonTopLevel("Charmander", "https://pokeapi.co/api/v2/pokemon/4"),
            PokemonTopLevel("Squirtle", "https://pokeapi.co/api/v2/pokemon/7")
        )
        stateFlow.value = PokedexViewState.Success(fakePokemonList, false, false)
    }

    override fun getNextPage() {

    }
}