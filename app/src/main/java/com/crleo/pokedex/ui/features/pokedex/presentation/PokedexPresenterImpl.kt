package com.crleo.pokedex.ui.features.pokedex.presentation

import com.crleo.pokedex.router.PokedexRouter
import com.crleo.pokedex.ui.features.pokedex.domain.PokedexInteractor
import com.crleo.pokedex.ui.features.pokedex.view.PokemonIntent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokedexPresenterImpl @Inject constructor(
    private val interactor: PokedexInteractor,
    private val pokedexRouter: PokedexRouter
): PokedexPresenter {
    private lateinit var scope: CoroutineScope

    private val _state = MutableStateFlow<PokedexViewState>(PokedexViewState.Loading)
    override val state: StateFlow<PokedexViewState> get() = _state

    private val limit = 50
    private var offset = 0

    override fun setScope(scope: CoroutineScope) {
        this.scope = scope
    }

    override fun getPokemonList() {
        scope.launch {
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
    }

    override fun getNextPage() {
        scope.launch {
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
                } catch (_: Exception) {

                }
            }
        }
    }

    override fun onPokemonItemClick(pokemonIntent: PokemonIntent) {
        when(pokemonIntent) {
            is PokemonIntent.PokemonClicked -> {
                pokedexRouter.navigateToPokemonDetails(pokemonId = pokemonIntent.index)
            }
        }
    }
}