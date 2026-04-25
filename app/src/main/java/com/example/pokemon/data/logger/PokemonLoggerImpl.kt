package com.example.pokemon.data.logger

import android.util.Log
import com.example.pokemon.BuildConfig

internal class PokemonLoggerImpl : PokemonLogger {

    override fun logError(throwable: Throwable, message: String) {
        if (BuildConfig.DEBUG) {
            Log.d("PokemonLoggerImpl", "$message: ${throwable.message}", throwable)
        }
    }
}