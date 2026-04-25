package com.example.pokemon.data.remote.util

import com.example.pokemon.data.remote.exceptions.PokemonException
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException

internal suspend fun <T> handleRequest(
    remoteCall: suspend () -> T
): T {
    try {
        return remoteCall()

    } catch (e: IOException) {
        throw PokemonException.NoInternet(e)

    } catch (e: HttpException) {
        throw when (e.code()) {
            401 -> PokemonException.Unauthorized(e)
            in 500..599 -> PokemonException.ServerError(e)
            in 400..499 -> PokemonException.Unknown(e)
            else -> PokemonException.Unknown(e)
        }

    } catch (e: SerializationException) {
        throw PokemonException.Error(e)

    } catch (e: Throwable) {
        throw PokemonException.Unknown(e)
    }
}