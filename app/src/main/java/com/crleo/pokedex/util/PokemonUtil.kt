package com.crleo.pokedex.util

import android.graphics.Color

object PokemonUtil {

    fun colourByType(type: String): Int {
        return when (type) {
            TypeConstants.FIRE -> Color.RED
            TypeConstants.WATER -> Color.BLUE
            TypeConstants.GRASS -> Color.GREEN
            TypeConstants.NORMAL -> Color.LTGRAY
            TypeConstants.ELECTRIC -> Color.YELLOW
            TypeConstants.POISON -> Color.MAGENTA
            TypeConstants.ROCK -> Color.GRAY
            TypeConstants.FLYING -> Color.CYAN
            else -> { Color.WHITE }
        }
    }
}