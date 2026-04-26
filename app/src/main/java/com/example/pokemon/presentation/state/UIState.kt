package com.example.pokemon.presentation.state

import com.example.pokemon.domain.error.PokemonFailure

sealed class UIState<out T> {

    data object Loading : UIState<Nothing>()

    data class Success<T>(val data: T) : UIState<T>()

    data class Fail(val failure: PokemonFailure) : UIState<Nothing>()
}