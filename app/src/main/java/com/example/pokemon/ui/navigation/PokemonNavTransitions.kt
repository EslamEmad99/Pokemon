package com.example.pokemon.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

internal const val PokemonNavAnimationDurationMillis = 400

internal fun pokemonEnterTransition(): EnterTransition {
    return slideInHorizontally(
        animationSpec = tween(PokemonNavAnimationDurationMillis),
        initialOffsetX = { fullWidth -> fullWidth }
    )
}

internal fun pokemonExitTransition(): ExitTransition {
    return slideOutHorizontally(
        animationSpec = tween(PokemonNavAnimationDurationMillis),
        targetOffsetX = { fullWidth -> -fullWidth }
    )
}

internal fun pokemonPopEnterTransition(): EnterTransition {
    return slideInHorizontally(
        animationSpec = tween(PokemonNavAnimationDurationMillis),
        initialOffsetX = { fullWidth -> -fullWidth }
    )
}

internal fun pokemonPopExitTransition(): ExitTransition {
    return slideOutHorizontally(
        animationSpec = tween(PokemonNavAnimationDurationMillis),
        targetOffsetX = { fullWidth -> fullWidth }
    )
}