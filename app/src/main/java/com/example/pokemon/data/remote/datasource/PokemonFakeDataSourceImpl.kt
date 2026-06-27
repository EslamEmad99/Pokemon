package com.example.pokemon.data.remote.datasource

import com.example.pokemon.data.remote.dto.PokemonDetailsDto
import com.example.pokemon.data.remote.dto.PokemonDto
import com.example.pokemon.data.remote.dto.TypeDto
import com.example.pokemon.data.remote.dto.TypeSlotDto
import com.example.pokemon.data.remote.exceptions.PokemonException
import kotlinx.coroutines.delay

internal class FakePokemonRemoteDataSource : PokemonRemoteDataSource {

    private val pokemons = listOf(
        PokemonDto(
            name = "bulbasaur",
            url = "https://pokeapi.co/api/v2/pokemon/1/"
        ),
        PokemonDto(
            name = "ivysaur",
            url = "https://pokeapi.co/api/v2/pokemon/2/"
        ),
        PokemonDto(
            name = "venusaur",
            url = "https://pokeapi.co/api/v2/pokemon/3/"
        ),
        PokemonDto(
            name = "charmander",
            url = "https://pokeapi.co/api/v2/pokemon/4/"
        ),
        PokemonDto(
            name = "charmeleon",
            url = "https://pokeapi.co/api/v2/pokemon/5/"
        ),
        PokemonDto(
            name = "charizard",
            url = "https://pokeapi.co/api/v2/pokemon/6/"
        ),
        PokemonDto(
            name = "squirtle",
            url = "https://pokeapi.co/api/v2/pokemon/7/"
        ),
        PokemonDto(
            name = "wartortle",
            url = "https://pokeapi.co/api/v2/pokemon/8/"
        ),
        PokemonDto(
            name = "blastoise",
            url = "https://pokeapi.co/api/v2/pokemon/9/"
        ),
        PokemonDto(
            name = "pikachu",
            url = "https://pokeapi.co/api/v2/pokemon/25/"
        )
    )

    override suspend fun getPokemonList(): List<PokemonDto> {
        delay(1000)
        return pokemons
    }

    override suspend fun getPokemonDetails(name: String): PokemonDetailsDto {
        delay(800)

        return when (name.lowercase()) {

            "bulbasaur" -> createPokemon(
                id = 1,
                name = "bulbasaur",
                height = 7,
                weight = 69,
                types = listOf("grass", "poison")
            )

            "ivysaur" -> createPokemon(
                id = 2,
                name = "ivysaur",
                height = 10,
                weight = 130,
                types = listOf("grass", "poison")
            )

            "venusaur" -> createPokemon(
                id = 3,
                name = "venusaur",
                height = 20,
                weight = 1000,
                types = listOf("grass", "poison")
            )

            "charmander" -> createPokemon(
                id = 4,
                name = "charmander",
                height = 6,
                weight = 85,
                types = listOf("fire")
            )

            "charmeleon" -> createPokemon(
                id = 5,
                name = "charmeleon",
                height = 11,
                weight = 190,
                types = listOf("fire")
            )

            "charizard" -> createPokemon(
                id = 6,
                name = "charizard",
                height = 17,
                weight = 905,
                types = listOf("fire", "flying")
            )

            "squirtle" -> createPokemon(
                id = 7,
                name = "squirtle",
                height = 5,
                weight = 90,
                types = listOf("water")
            )

            "wartortle" -> createPokemon(
                id = 8,
                name = "wartortle",
                height = 10,
                weight = 225,
                types = listOf("water")
            )

            "blastoise" -> createPokemon(
                id = 9,
                name = "blastoise",
                height = 16,
                weight = 855,
                types = listOf("water")
            )

            "pikachu" -> createPokemon(
                id = 25,
                name = "pikachu",
                height = 4,
                weight = 60,
                types = listOf("electric")
            )

            else -> throw PokemonException.Unknown()
        }
    }

    private fun createPokemon(
        id: Int,
        name: String,
        height: Int,
        weight: Int,
        types: List<String>
    ) = PokemonDetailsDto(
        id = id,
        name = name,
        height = height,
        weight = weight,
        types = types.map { type ->
            TypeSlotDto(
                type = TypeDto(type)
            )
        }
    )
}
