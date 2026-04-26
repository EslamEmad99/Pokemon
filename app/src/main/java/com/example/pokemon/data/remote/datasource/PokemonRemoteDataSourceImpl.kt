package com.example.pokemon.data.remote.datasource

import com.example.pokemon.data.remote.api.PokemonApi
import com.example.pokemon.data.remote.dto.PokemonDetailsDto
import com.example.pokemon.data.remote.dto.PokemonDto
import com.example.pokemon.data.remote.dto.TypeDto
import com.example.pokemon.data.remote.dto.TypeSlotDto
import com.example.pokemon.data.remote.util.handleRequest

internal class PokemonRemoteDataSourceImpl(
    private val api: PokemonApi
) : PokemonRemoteDataSource {

    override suspend fun getPokemonList(): List<PokemonDto> {
        return handleRequest {
            api.getPokemonList().results
        }
    }

    override suspend fun getPokemonDetails(name: String): PokemonDetailsDto {
        return handleRequest {
            api.getPokemonDetails(name)
        }
    }
}
