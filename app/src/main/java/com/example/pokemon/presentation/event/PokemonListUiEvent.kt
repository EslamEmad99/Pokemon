package com.example.pokemon.presentation.event

sealed interface PokemonListUiEvent {
    data class OnPokemonClicked(val name: String) : PokemonListUiEvent
}