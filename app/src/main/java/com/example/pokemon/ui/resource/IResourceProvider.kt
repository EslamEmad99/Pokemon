package com.example.pokemon.ui.resource

import androidx.annotation.StringRes

interface IResourceProvider {
    fun getString(@StringRes resId: Int, vararg values: Any): String
}