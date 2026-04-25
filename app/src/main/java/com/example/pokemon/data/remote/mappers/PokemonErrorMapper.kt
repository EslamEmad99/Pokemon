package com.example.pokemon.data.remote.mappers

import com.example.pokemon.data.remote.exceptions.PokemonException
import com.example.pokemon.domain.error.PokemonFailure

fun PokemonException.toPokemonFailure(): PokemonFailure {
    return when (this) {
        is PokemonException.NoInternet -> PokemonFailure.NoInternet
        is PokemonException.Unauthorized -> PokemonFailure.Unauthorized
        is PokemonException.ServerError -> PokemonFailure.ServerError
        is PokemonException.Error -> PokemonFailure.DataError
        else -> PokemonFailure.Unknown
    }
}
