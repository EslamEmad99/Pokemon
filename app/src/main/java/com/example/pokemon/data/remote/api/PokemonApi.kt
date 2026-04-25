package com.example.pokemon.data.remote.api

import com.example.pokemon.data.remote.dto.PokemonDetailsDto
import com.example.pokemon.data.remote.dto.PokemonListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemonList(): PokemonListResponseDto

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(
        @Path("name") name: String
    ): PokemonDetailsDto
}
