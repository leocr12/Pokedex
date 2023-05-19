package com.crleo.pokedex.util

object ImageUrlProvider {
    private const val IMAGE_BASE_URL = "https://pokeapi.co/media/sprites/pokemon/"

    fun getImageUrl(pokemonId: Int): String {
        return "$IMAGE_BASE_URL.png"
    }
}