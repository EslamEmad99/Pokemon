package com.example.pokemon.data.local.cache

import com.example.pokemon.data.remote.dto.PokemonDto

class PokemonMemoryCache {

    private var pokemons: List<PokemonDto> = emptyList()

    fun getPokemons(): List<PokemonDto> {
        return pokemons
    }

    fun setPokemons(pokemons: List<PokemonDto>) {
        this.pokemons = pokemons
    }

    fun clear() {
        pokemons = emptyList()
    }
}
