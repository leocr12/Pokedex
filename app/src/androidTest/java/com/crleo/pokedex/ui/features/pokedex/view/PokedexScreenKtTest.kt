package com.crleo.pokedex.ui.features.pokedex.view

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.crleo.pokedex.mocks.MockPokedexPresenter
import com.crleo.pokedex.ui.theme.PokedexTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class PokedexScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var mockPokedexPresenter: MockPokedexPresenter

    @Before
    fun setup() {
        mockPokedexPresenter = MockPokedexPresenter()

        composeTestRule.setContent {
            PokedexTheme {
                PokedexScreen(presenter = mockPokedexPresenter)
            }

            mockPokedexPresenter.getPokemonList()
        }
    }

    @Test
    fun testPokedexScreenSuccessState() {
        composeTestRule.onNodeWithText("Bulbasaur").assertIsDisplayed()
        composeTestRule.onNodeWithText("Charmander").assertIsDisplayed()
        composeTestRule.onNodeWithText("Squirtle").assertIsDisplayed()
    }

//    @Test
//    fun testOpenPokemonDetails() {
//        val pokemonId = 1
//        mockPokedexPresenter.onPokemonItemClick(pokemonId)
//        verify(pokedexRouter)
//    }
}