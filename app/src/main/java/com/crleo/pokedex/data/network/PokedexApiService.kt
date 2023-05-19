package com.crleo.pokedex.data.network

import com.crleo.pokedex.data.model.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexApiService {
    @GET("pokemon/{pokemonName}")
    suspend fun getPokemonDetails(@Path("pokemonName") pokemonName: String): Response<Pokemon>
}