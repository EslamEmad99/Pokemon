package com.example.pokemon.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.domain.model.PokemonResult
import com.example.pokemon.domain.usecase.GetPokemonDetailsUseCase
import com.example.pokemon.presentation.event.PokemonDetailsUiEvent
import com.example.pokemon.presentation.mapper.toUiModel
import com.example.pokemon.presentation.sideeffect.PokemonSideEffect
import com.example.pokemon.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : ViewModel() {

    private val pokemonName: String = checkNotNull(savedStateHandle["name"])

    private val _uiState = MutableStateFlow<UIState<com.example.pokemon.presentation.model.PokemonDetailsUiModel>>(UIState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<PokemonSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        loadPokemonDetails()
    }

    fun onEvent(event: PokemonDetailsUiEvent) {
        when (event) {
            PokemonDetailsUiEvent.OnBackClicked -> navigateBack()
        }
    }

    private fun loadPokemonDetails() {
        viewModelScope.launch {
            _uiState.update { UIState.Loading }

            when (val result = getPokemonDetailsUseCase(pokemonName)) {
                is PokemonResult.Success -> {
                    _uiState.update {
                        UIState.Success(result.data.toUiModel())
                    }
                }

                is PokemonResult.Error -> {
                    _uiState.update {
                        UIState.Fail(result.failure)
                    }
                }
            }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _sideEffect.emit(PokemonSideEffect.NavigateBack)
        }
    }
}