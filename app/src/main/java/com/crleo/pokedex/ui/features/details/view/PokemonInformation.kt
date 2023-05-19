package com.crleo.pokedex.ui.features.details.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.crleo.pokedex.data.model.PokemonDetails
import com.crleo.pokedex.util.PokemonUtil
import java.util.*

@Composable
fun PokemonInformation(pokemonDetails: PokemonDetails) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(horizontal = 16.dp)),
    ) {
        Spacer(modifier = Modifier.size(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = pokemonDetails.sprites.frontDefault,
                contentDescription = pokemonDetails.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.inverseSurface)
                    .size(160.dp)
            )
        }

        Text(
            text = "#${pokemonDetails.id}",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = pokemonDetails.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            style = MaterialTheme.typography.headlineLarge
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            pokemonDetails.types.forEach { type ->
                val typeName = type.type.name.uppercase()
                val typeColour = PokemonUtil.colourByType(type = typeName)
                Box(
                    modifier = Modifier
                        .clip(RectangleShape)
                        .background(Color(typeColour)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = typeName, modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}