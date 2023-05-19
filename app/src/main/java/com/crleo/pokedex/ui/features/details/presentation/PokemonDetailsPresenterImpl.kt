package com.crleo.pokedex.ui.features.details.presentation

import com.crleo.pokedex.ui.features.details.domain.PokemonDetailsInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonDetailsPresenterImpl @Inject constructor(private val interactor: PokemonDetailsInteractor): PokemonDetailsPresenter {
    private lateinit var scope: CoroutineScope

    private val _state = MutableStateFlow<PokemonDetailsViewState>(PokemonDetailsViewState.Loading)
    override val state: StateFlow<PokemonDetailsViewState>
        get() = _state

    override fun setScope(scope: CoroutineScope) {
        this.scope = scope
    }

    override fun getPokemonDetails(pokemonId: Int) {
        scope.launch {
            _state.value = PokemonDetailsViewState.Loading
            try {
                val pokemonDetails = interactor.getPokemonDetails(pokemonId)
                _state.value = PokemonDetailsViewState.Success(pokemonDetails = pokemonDetails)
            } catch (e: Exception) {
                _state.value = PokemonDetailsViewState.Error(e.message ?: "An error occurred")
            }
        }
    }
}