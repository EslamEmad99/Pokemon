package com.example.pokemon.domain.repository

import com.example.pokemon.domain.model.Pokemon
import com.example.pokemon.domain.model.PokemonDetails
import com.example.pokemon.domain.model.PokemonResult

interface PokemonRepository {
    suspend fun getPokemonList(): PokemonResult<List<Pokemon>>

    suspend fun getPokemonDetails(name: String): PokemonResult<PokemonDetails>
}