package com.example.pokemon.domain.usecase

import com.example.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke() = repository.getPokemonList()
}