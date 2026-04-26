package com.example.pokemon.presentation.sideeffect

sealed interface PokemonSideEffect {

    data class NavigateToDetails(val name: String) : PokemonSideEffect

    data object NavigateBack : PokemonSideEffect
}