package com.crleo.pokedex.ui.features.details.data

import com.crleo.pokedex.data.model.PokemonDetails
import com.crleo.pokedex.data.network.ApiException
import com.crleo.pokedex.data.network.PokedexApiService
import javax.inject.Inject

class PokemonDetailsRepositoryImpl @Inject constructor(private val apiService: PokedexApiService): PokemonDetailsRepository {
    override suspend fun getPokemonDetails(pokemonId: Int): PokemonDetails {
        val response = apiService.getPokemonDetails(pokemonId)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw ApiException("Failed to fetch Pokemon details")
        }
    }
}