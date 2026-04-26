package com.example.pokemon.presentation.mapper

import com.example.pokemon.domain.model.Pokemon
import com.example.pokemon.domain.model.PokemonDetails
import com.example.pokemon.presentation.model.PokemonDetailsUiModel
import com.example.pokemon.presentation.model.PokemonUiModel

internal fun Pokemon.toUiModel(): PokemonUiModel {
    return PokemonUiModel(
        id = id,
        name = name
    )
}

internal fun PokemonDetails.toUiModel(): PokemonDetailsUiModel {
    return PokemonDetailsUiModel(
        name = name,
        imageUrl = imageUrl,
        height = height,
        weight = weight,
        types = types
    )
}