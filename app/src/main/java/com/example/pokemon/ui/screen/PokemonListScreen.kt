package com.example.pokemon.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokemon.presentation.event.PokemonListUiEvent
import com.example.pokemon.presentation.mapper.toMessageRes
import com.example.pokemon.presentation.model.PokemonUiModel
import com.example.pokemon.presentation.state.UIState
import com.example.pokemon.ui.locals.LocalResourceProvider

@Composable
fun PokemonListScreen(
    uiState: UIState<List<PokemonUiModel>>,
    onEvent: (PokemonListUiEvent) -> Unit
) {
    val strings = LocalResourceProvider.current

    when (uiState) {
        UIState.Loading -> {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                CircularProgressIndicator()
            }
        }

        is UIState.Fail -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = strings.getString(uiState.failure.toMessageRes()))
            }
        }

        is UIState.Success -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(uiState.data) { item ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onEvent(PokemonListUiEvent.OnPokemonClicked(item.name)) }
                            .padding(16.dp)
                    ) {
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    HorizontalDivider()
                }
            }
        }
    }
}