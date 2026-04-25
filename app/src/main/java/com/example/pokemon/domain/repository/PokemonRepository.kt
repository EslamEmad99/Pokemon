package com.example.pokemon.domain.repository

import com.example.pokemon.domain.model.Pokemon
import com.example.pokemon.domain.model.PokemonDetails

interface PokemonRepository {
    suspend fun getPokemonList(): List<Pokemon>

    suspend fun getPokemonDetails(name: String): PokemonDetails
}