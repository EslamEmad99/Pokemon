package com.example.pokemon.presentation.event

sealed interface PokemonDetailsUiEvent {
    data object OnBackClicked : PokemonDetailsUiEvent
}