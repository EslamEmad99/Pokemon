package com.example.pokemon.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailsDto(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<TypeSlotDto>
)

@Serializable
data class TypeSlotDto(
    val type: TypeDto
)

@Serializable
data class TypeDto(
    val name: String
)