package com.crleo.pokedex.router

import androidx.navigation.NavHostController
import javax.inject.Inject

class PokedexRouterImpl @Inject constructor(private val navControllerHolder: NavControllerHolder): PokedexRouter {
    override fun navigateToPokemonDetails(pokemonId: Int) {
        navControllerHolder.navHostController.navigate("pokemonDetails/$pokemonId")
    }
}