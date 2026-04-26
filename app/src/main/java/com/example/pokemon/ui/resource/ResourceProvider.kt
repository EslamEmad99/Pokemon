package com.example.pokemon.ui.resource

import android.content.Context
import androidx.annotation.StringRes

class ResourceProvider(
    private val context: Context
) : IResourceProvider {

    override fun getString(@StringRes resId: Int, vararg values: Any): String {
        return context.getString(resId, *values)
    }
}