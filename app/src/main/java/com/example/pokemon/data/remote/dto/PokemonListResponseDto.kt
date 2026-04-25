package com.example.pokemon.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponseDto(
    val results: List<PokemonDto>
)

@Serializable
data class PokemonDto(
    val name: String,
    val url: String
)
