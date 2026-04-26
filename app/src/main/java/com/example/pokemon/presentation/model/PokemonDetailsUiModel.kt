package com.example.pokemon.presentation.model

data class PokemonDetailsUiModel(
    val name: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
    val types: List<String>
)
