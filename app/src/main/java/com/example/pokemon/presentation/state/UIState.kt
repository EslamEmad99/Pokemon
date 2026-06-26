package com.example.pokemon.presentation.state

import com.example.pokemon.domain.error.PokemonFailure

sealed interface UIState<out T> {

    data object Loading : UIState<Nothing>

    data class Success<T>(
        val data: T
    ) : UIState<T>

    data object Empty : UIState<Nothing>

    data class Fail(
        val failure: PokemonFailure
    ) : UIState<Nothing>
}