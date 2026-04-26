package com.example.pokemon.presentation.mapper

import androidx.annotation.StringRes
import com.example.pokemon.R
import com.example.pokemon.domain.error.PokemonFailure

@StringRes
internal fun PokemonFailure.toMessageRes(): Int {
    return when (this) {
        PokemonFailure.NoInternet -> R.string.pokemon_error_no_internet
        PokemonFailure.Unauthorized -> R.string.pokemon_error_unauthorized
        PokemonFailure.ServerError -> R.string.pokemon_error_server_error
        PokemonFailure.DataError -> R.string.pokemon_error_data_error
        PokemonFailure.Unknown -> R.string.pokemon_error_unknown
    }
}