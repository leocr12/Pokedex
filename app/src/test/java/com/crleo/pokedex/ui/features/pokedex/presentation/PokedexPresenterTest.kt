package com.crleo.pokedex.ui.features.pokedex.presentation

import com.crleo.pokedex.data.model.PokemonTopLevel
import com.crleo.pokedex.ui.features.pokedex.domain.PokedexInteractor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class PokedexPresenterTest {

    @Mock
    private lateinit var pokedexInteractor: PokedexInteractor

    private lateinit var pokedexPresenter: PokedexPresenterImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        pokedexPresenter = PokedexPresenterImpl(pokedexInteractor)
    }

    @Test
    fun `test getPokemonList success`() = runBlocking {
        val pokemonList = listOf(
            PokemonTopLevel("Bulbasaur", "https://pokeapi.co/api/v2/pokemon/1"),
            PokemonTopLevel("Charmander", "https://pokeapi.co/api/v2/pokemon/4"),
            PokemonTopLevel("Squirtle", "https://pokeapi.co/api/v2/pokemon/7")
        )

        val expectedViewState = PokedexViewState.Success(pokemonList, false, false)
        `when`(pokedexInteractor.getPokemonList(50, 0)).thenReturn(pokemonList)

        pokedexPresenter.setScope(this)
        pokedexPresenter.getPokemonList()

        val emittedStates = pokedexPresenter.state.take(2).toList()
        assertEquals(emittedStates.last(), expectedViewState)
    }

    @Test
    fun `test getPokemonList error`() = runBlocking {
        val errorMessage = "Error fetching Pokemon list"
        val expectedViewState = PokedexViewState.Error(errorMessage = errorMessage)

        `when`(pokedexInteractor.getPokemonList(50, 0)).thenThrow(java.lang.RuntimeException(errorMessage))

        pokedexPresenter.setScope(this)
        pokedexPresenter.getPokemonList()

        val emittedStates = pokedexPresenter.state.take(2).toList()
        assertEquals(emittedStates.last(), expectedViewState)
    }
}