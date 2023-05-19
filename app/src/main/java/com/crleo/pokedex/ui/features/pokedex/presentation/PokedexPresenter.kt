package com.crleo.pokedex.ui.features.pokedex.presentation

import com.crleo.pokedex.ui.features.pokedex.domain.PokedexInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface PokedexPresenter {
    val state: StateFlow<PokedexViewState>
    fun setScope(scope: CoroutineScope)
    fun getPokemonList()
    fun getNextPage()
    fun onPokemonItemClick(pokemonId: Int)
}