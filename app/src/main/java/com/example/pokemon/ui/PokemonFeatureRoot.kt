package com.example.pokemon.ui

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.pokemon.ui.locals.LocalResourceProvider
import com.example.pokemon.ui.navigation.PokemonNavGraph
import com.example.pokemon.ui.resource.ResourceProvider

@Composable
fun PokemonFeatureRoot() {
    val context = LocalContext.current
    val resourceProvider = remember(context) {
        ResourceProvider(context.applicationContext)
    }
    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalResourceProvider provides resourceProvider
    ) {
        PokemonNavGraph(navController = navController)
    }
}