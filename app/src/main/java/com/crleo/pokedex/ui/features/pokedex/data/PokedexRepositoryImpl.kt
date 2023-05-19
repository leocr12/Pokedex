package com.crleo.pokedex.ui.features.pokedex.data

import com.crleo.pokedex.data.model.PokemonDetails
import com.crleo.pokedex.data.model.PokemonTopLevel
import com.crleo.pokedex.data.network.ApiException
import com.crleo.pokedex.data.network.PokedexApiService
import javax.inject.Inject

class PokedexRepositoryImpl @Inject constructor(private val apiService: PokedexApiService): PokedexRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): List<PokemonTopLevel> {
        val response = apiService.getPokemonList(limit = limit, offset = offset)
        if (response.isSuccessful) {
            return response.body()?.results ?: emptyList()
        } else {
            throw ApiException("Failed to fetch Pokemon list")
        }
    }
}