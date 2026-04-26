package com.example.pokemon.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface PokemonRoute {

    @Serializable
    data object List : PokemonRoute

    @Serializable
    data class Details(val name: String) : PokemonRoute
}