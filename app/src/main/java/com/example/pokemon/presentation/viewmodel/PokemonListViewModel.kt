package com.example.pokemon.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.domain.model.PokemonResult
import com.example.pokemon.domain.usecase.GetPokemonListUseCase
import com.example.pokemon.domain.usecase.SearchPokemonUseCase
import com.example.pokemon.presentation.event.PokemonListUiEvent
import com.example.pokemon.presentation.mapper.toUiModel
import com.example.pokemon.presentation.model.PokemonUiModel
import com.example.pokemon.presentation.sideeffect.PokemonSideEffect
import com.example.pokemon.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val searchPokemonUseCase: SearchPokemonUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<List<PokemonUiModel>>>(UIState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow<String?>(null)
    val searchQuery = _searchQuery.asStateFlow()

    private val _sideEffect = MutableSharedFlow<PokemonSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        observeSearch()
        loadPokemonList()
    }

    private fun observeSearch() {
        viewModelScope.launch {
            searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .collectLatest { query ->
                    query?.let {
                        search(it)
                    }
                }
        }
    }

    fun onEvent(event: PokemonListUiEvent) {
        when (event) {
            is PokemonListUiEvent.OnPokemonClicked -> navigateToDetails(event.name)

            is PokemonListUiEvent.OnSearchQueryChanged -> {
                _searchQuery.update { event.query }
            }

            PokemonListUiEvent.OnRetryClicked -> loadPokemonList()
        }
    }

    private fun navigateToDetails(name: String) {
        viewModelScope.launch {
            _sideEffect.emit(PokemonSideEffect.NavigateToDetails(name))
        }
    }

    private fun search(query: String) {
        println("my_pokemons search $query")
        viewModelScope.launch {
            when (val result = searchPokemonUseCase(query)) {
                is PokemonResult.Success -> {
                    _uiState.update {
                        UIState.Success(data = result.data.map { it.toUiModel() })
                    }
                }

                is PokemonResult.Error -> {
                    _uiState.update {
                        UIState.Fail(failure = result.failure)
                    }
                }
            }
        }
    }

    private fun loadPokemonList() {
        viewModelScope.launch {
            _uiState.update { UIState.Loading }

            when (val result = getPokemonListUseCase()) {
                is PokemonResult.Success -> {
                    _uiState.update {
                        UIState.Success(data = result.data.map { it.toUiModel() })
                    }
                }

                is PokemonResult.Error -> {
                    _uiState.update {
                        UIState.Fail(failure = result.failure)
                    }
                }
            }
        }
    }
}