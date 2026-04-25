package com.example.pokemon.data.remote.datasource

import com.example.pokemon.data.remote.dto.PokemonDetailsDto
import com.example.pokemon.data.remote.dto.PokemonDto

interface PokemonRemoteDataSource {

    suspend fun getPokemonList(): List<PokemonDto>

    suspend fun getPokemonDetails(name: String): PokemonDetailsDto
}