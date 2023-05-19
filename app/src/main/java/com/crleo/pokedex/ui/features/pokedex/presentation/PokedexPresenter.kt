package com.crleo.pokedex.ui.features.pokedex.presentation

import com.crleo.pokedex.ui.features.pokedex.domain.PokedexInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokedexPresenter @Inject constructor(
    private val interactor: PokedexInteractor,
) {
    private lateinit var scope: CoroutineScope

    private val _state = MutableStateFlow<PokedexViewState>(PokedexViewState.Loading)
    val state: StateFlow<PokedexViewState> get() = _state

    private val limit = 50
    private var offset = 0

    fun setScope(scope: CoroutineScope) {
        this.scope = scope
    }

    fun getPokemonList() = scope.launch {
        _state.value = PokedexViewState.Loading
        try {
            val pokemonList = interactor.getPokemonList(limit, offset)
            _state.value = PokedexViewState.Success(
                pokemonTopLevelList = pokemonList,
                isLoading = false,
                isError = false,
            )
        } catch (e: Exception) {
            _state.value = PokedexViewState.Error(e.message ?: "An error occurred")
        }
    }

    fun getNextPage() = scope.launch {
        val currentState = _state.value
        if (currentState is PokedexViewState.Success) {
            val currentPokemonList = currentState.pokemonTopLevelList
            offset += limit
            try {
                val pokemonList = interactor.getPokemonList(limit, offset)
                _state.value = PokedexViewState.Success(
                    pokemonTopLevelList = currentPokemonList + pokemonList,
                    isLoading = false,
                    isError = false
                )
            } catch (e: Exception) {

            }
        }
    }
}