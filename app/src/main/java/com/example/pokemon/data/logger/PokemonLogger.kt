package com.example.pokemon.data.logger

interface PokemonLogger {

    fun logError(throwable: Throwable, message: String = "")
}