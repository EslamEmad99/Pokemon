package com.example.pokemon.domain.usecase

import com.example.pokemon.domain.model.Pokemon
import com.example.pokemon.domain.model.PokemonResult
import com.example.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class SearchPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(query: String): PokemonResult<List<Pokemon>> {
        return repository.searchPokemon(query)
    }
}