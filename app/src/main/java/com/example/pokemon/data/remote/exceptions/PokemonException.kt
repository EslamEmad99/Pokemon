package com.example.pokemon.data.remote.exceptions

sealed class PokemonException(cause: Throwable? = null) : Exception(cause) {

    class NoInternet(cause: Throwable? = null) : PokemonException(cause)

    class Unauthorized(cause: Throwable? = null) : PokemonException(cause)

    class ServerError(cause: Throwable? = null) : PokemonException(cause)

    class Error(cause: Throwable? = null) : PokemonException(cause)

    class Unknown(cause: Throwable? = null) : PokemonException(cause)
}
