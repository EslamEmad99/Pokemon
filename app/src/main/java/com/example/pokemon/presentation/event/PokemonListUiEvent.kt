package com.example.pokemon.presentation.event

sealed interface PokemonListUiEvent {
    data class OnPokemonClicked(val name: String) : PokemonListUiEvent

    data class OnSearchQueryChanged(val query: String) : PokemonListUiEvent

    data object OnRetryClicked : PokemonListUiEvent
}