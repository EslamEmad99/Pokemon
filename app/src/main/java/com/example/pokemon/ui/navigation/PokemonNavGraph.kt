package com.example.pokemon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pokemon.presentation.sideeffect.PokemonSideEffect
import com.example.pokemon.presentation.viewmodel.PokemonDetailsViewModel
import com.example.pokemon.presentation.viewmodel.PokemonListViewModel
import com.example.pokemon.ui.screen.PokemonDetailsScreen
import com.example.pokemon.ui.screen.PokemonListScreen
import kotlinx.coroutines.flow.Flow

@Composable
fun PokemonNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = PokemonRoute.List,
        enterTransition = { pokemonEnterTransition() },
        exitTransition = { pokemonExitTransition() },
        popEnterTransition = { pokemonPopEnterTransition() },
        popExitTransition = { pokemonPopExitTransition() }
    ) {
        composable<PokemonRoute.List> {
            PokemonListRoute(
                onNavigateToDetails = { name ->
                    navController.navigate(PokemonRoute.Details(name))
                }
            )
        }

        composable<PokemonRoute.Details> {
            PokemonDetailsRoute(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
private fun PokemonListRoute(
    onNavigateToDetails: (String) -> Unit
) {
    val viewModel: PokemonListViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    CollectPokemonSideEffects(
        sideEffect = viewModel.sideEffect,
        onSideEffect = { effect ->
            when (effect) {
                is PokemonSideEffect.NavigateToDetails -> onNavigateToDetails(effect.name)
                PokemonSideEffect.NavigateBack -> Unit
            }
        }
    )

    PokemonListScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun PokemonDetailsRoute(
    onNavigateBack: () -> Unit
) {
    val viewModel: PokemonDetailsViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    CollectPokemonSideEffects(
        sideEffect = viewModel.sideEffect,
        onSideEffect = { effect ->
            when (effect) {
                PokemonSideEffect.NavigateBack -> onNavigateBack()
                is PokemonSideEffect.NavigateToDetails -> Unit
            }
        }
    )

    PokemonDetailsScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun <T> CollectPokemonSideEffects(
    sideEffect: Flow<T>,
    onSideEffect: (T) -> Unit
) {
    LaunchedEffect(sideEffect) {
        sideEffect.collect { effect ->
            onSideEffect(effect)
        }
    }
}