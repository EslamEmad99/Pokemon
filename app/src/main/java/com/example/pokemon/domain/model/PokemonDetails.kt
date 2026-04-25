package com.example.pokemon.domain.model

data class PokemonDetails(
    val name: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
    val types: List<String>
)
