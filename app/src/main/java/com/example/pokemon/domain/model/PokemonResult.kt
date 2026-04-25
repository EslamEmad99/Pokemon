package com.example.pokemon.domain.model

import com.example.pokemon.domain.error.PokemonFailure

sealed class PokemonResult<out T> {

    data class Success<T>(val data: T) : PokemonResult<T>()

    data class Error(val failure: PokemonFailure) : PokemonResult<Nothing>()
}