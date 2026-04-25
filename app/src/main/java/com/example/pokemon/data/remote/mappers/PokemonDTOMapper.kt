package com.example.pokemon.data.remote.mappers

import com.example.pokemon.data.remote.dto.PokemonDetailsDto
import com.example.pokemon.data.remote.dto.PokemonDto
import com.example.pokemon.domain.model.Pokemon
import com.example.pokemon.domain.model.PokemonDetails

fun PokemonDto.toDomain(): Pokemon {
    return Pokemon(
        id = extractIdFromUrl(url),
        name = name
    )
}

fun PokemonDetailsDto.toDomain(): PokemonDetails {
    return PokemonDetails(
        name = name,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
        height = height,
        weight = weight,
        types = types.map { it.type.name }
    )
}

private fun extractIdFromUrl(url: String): Int {
    return url.trimEnd('/').substringAfterLast('/').toInt()
}
