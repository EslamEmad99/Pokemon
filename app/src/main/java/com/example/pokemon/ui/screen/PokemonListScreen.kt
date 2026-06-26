package com.example.pokemon.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokemon.R
import com.example.pokemon.domain.error.PokemonFailure
import com.example.pokemon.presentation.event.PokemonListUiEvent
import com.example.pokemon.presentation.mapper.toMessageRes
import com.example.pokemon.presentation.model.PokemonUiModel
import com.example.pokemon.presentation.state.UIState
import com.example.pokemon.ui.locals.LocalResourceProvider

@Composable
fun PokemonListScreen(
    uiState: UIState<List<PokemonUiModel>>,
    searchQuery: String,
    onEvent: (PokemonListUiEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        SearchBar(
            query = searchQuery,
            onQueryChanged = {
                onEvent(PokemonListUiEvent.OnSearchQueryChanged(it))
            }
        )

        when (uiState) {
            UIState.Empty -> Unit

            UIState.Loading -> LoadingContent()

            is UIState.Fail -> ErrorContent(
                failure = uiState.failure,
                onRetry = {
                    onEvent(PokemonListUiEvent.OnRetryClicked)
                }
            )

            is UIState.Success -> SuccessContent(
                pokemons = uiState.data,
                onPokemonClicked = {
                    onEvent(PokemonListUiEvent.OnPokemonClicked(it))
                }
            )
        }
    }
}

@Composable
private fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit
) {
    val strings = LocalResourceProvider.current

    OutlinedTextField(
        value = query,
        onValueChange = onQueryChanged,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        singleLine = true,
        label = {
            Text(strings.getString(R.string.search_pokemons))
        }
    )
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorContent(
    failure: PokemonFailure,
    onRetry: () -> Unit
) {
    val strings = LocalResourceProvider.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = strings.getString(failure.toMessageRes())
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onRetry) {
            Text(strings.getString(R.string.retry))
        }
    }
}

@Composable
private fun SuccessContent(
    pokemons: List<PokemonUiModel>,
    onPokemonClicked: (String) -> Unit
) {
    if (pokemons.isEmpty()) {
        NoResultsContent()
    } else {
        PokemonList(
            pokemons = pokemons,
            onPokemonClicked = onPokemonClicked
        )
    }
}

@Composable
private fun NoResultsContent() {
    val strings = LocalResourceProvider.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = strings.getString(R.string.no_pokemons)
        )
    }
}

@Composable
private fun PokemonList(
    pokemons: List<PokemonUiModel>,
    onPokemonClicked: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = pokemons,
            key = { it.id }
        ) { pokemon ->

            PokemonItem(
                pokemon = pokemon,
                onClick = {
                    onPokemonClicked(pokemon.name)
                }
            )
        }
    }
}

@Composable
private fun PokemonItem(
    pokemon: PokemonUiModel,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.titleMedium
        )

        HorizontalDivider()
    }
}