package com.crleo.pokedex.data.network

import com.crleo.pokedex.data.model.PokemonDetails
import com.crleo.pokedex.data.model.PokemonTopLevel
import com.crleo.pokedex.data.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexApiService {

    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Response<PokemonListResponse>

    @GET("pokemon/{pokemonId}")
    suspend fun getPokemonDetails(@Path("pokemonId") pokemonId: Int): Response<PokemonDetails>
}