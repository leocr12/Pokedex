package com.crleo.pokedex.ui.features.pokedex.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.crleo.pokedex.data.model.PokemonTopLevel
import java.util.*

@Composable
fun PokemonItem(pokemon: PokemonTopLevel, index: Int) {
    Row(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
    ) {
        Text(
            text = "$index",
            modifier = Modifier
                .padding(end = 16.dp)
                .width(32.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = pokemon.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            style = MaterialTheme.typography.bodyLarge
        )
    }
}