package com.crleo.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.crleo.pokedex.router.App
import com.crleo.pokedex.router.NavControllerHolder
import com.crleo.pokedex.router.PokedexRouter
import com.crleo.pokedex.ui.features.details.presentation.PokemonDetailsPresenter
import com.crleo.pokedex.ui.features.details.view.PokemonDetailsScreen
import com.crleo.pokedex.ui.features.pokedex.presentation.PokedexPresenter
import com.crleo.pokedex.ui.features.pokedex.view.PokedexScreen
import com.crleo.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navControllerHolder: NavControllerHolder

    @Inject
    lateinit var presenter: PokedexPresenter

    @Inject
    lateinit var detailsPresenter: PokemonDetailsPresenter

    private val coroutineScope: CoroutineScope by lazy { lifecycleScope }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            navControllerHolder.navHostController = navController
            App(
                navController = navController,
                presenter = presenter,
                detailsPresenter = detailsPresenter
            )
        }

        presenter.setScope(coroutineScope)
        detailsPresenter.setScope(coroutineScope)

        presenter.getPokemonList()
    }
}