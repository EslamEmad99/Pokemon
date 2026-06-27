package com.example.pokemon.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokemon.R
import com.example.pokemon.presentation.event.PokemonDetailsUiEvent
import com.example.pokemon.presentation.mapper.toMessageRes
import com.example.pokemon.presentation.model.PokemonDetailsUiModel
import com.example.pokemon.presentation.state.UIState
import com.example.pokemon.ui.locals.LocalResourceProvider

@Composable
fun PokemonDetailsScreen(
    uiState: UIState<PokemonDetailsUiModel>,
    onEvent: (PokemonDetailsUiEvent) -> Unit
) {
    val strings = LocalResourceProvider.current

    when (uiState) {
        UIState.Empty -> {}

        UIState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        is UIState.Fail -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = strings.getString(uiState.failure.toMessageRes()))
            }
        }

        is UIState.Success -> {
            val item = uiState.data

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = item.name)
                Text(text = strings.getString(R.string.pokemon_height, item.height))
                Text(text = strings.getString(R.string.pokemon_weight, item.weight))
                Text(
                    text = strings.getString(
                        R.string.pokemon_types,
                        item.types.joinToString()
                    )
                )
                Text(text = item.imageUrl)
            }
        }
    }
}