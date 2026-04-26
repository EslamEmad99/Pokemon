package com.example.pokemon.ui.locals

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.pokemon.ui.resource.IResourceProvider

val LocalResourceProvider = staticCompositionLocalOf<IResourceProvider> {
    error("No IResourceProvider provided")
}