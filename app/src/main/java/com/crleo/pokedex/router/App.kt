package com.crleo.pokedex.router

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.crleo.pokedex.ui.features.details.presentation.PokemonDetailsPresenter
import com.crleo.pokedex.ui.features.details.view.PokemonDetailsScreen
import com.crleo.pokedex.ui.features.pokedex.presentation.PokedexPresenter
import com.crleo.pokedex.ui.features.pokedex.view.PokedexScreen
import com.crleo.pokedex.ui.theme.PokedexTheme

@Composable
fun App(
    navController: NavHostController,
    presenter: PokedexPresenter,
    detailsPresenter: PokemonDetailsPresenter
) {
    NavHost(navController = navController, startDestination = "pokedex") {
        composable("pokedex") {
            PokedexTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PokedexScreen(presenter = presenter)
                }
            }
        }

        composable("pokemonDetails/{pokemonId}") { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getString("pokemonId")?.toInt() ?: -1
            PokedexTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PokemonDetailsScreen(pokemonId = pokemonId, presenter = detailsPresenter)
                }
            }
        }
    }
}