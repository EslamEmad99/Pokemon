package com.example.pokemon.domain.error

sealed class PokemonFailure {
    data object NoInternet : PokemonFailure()
    data object Unauthorized : PokemonFailure()
    data object ServerError : PokemonFailure()
    data object DataError : PokemonFailure()
    data object Unknown : PokemonFailure()
}