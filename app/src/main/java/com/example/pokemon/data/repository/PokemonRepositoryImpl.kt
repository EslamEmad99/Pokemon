package com.example.pokemon.data.repository

import com.example.pokemon.data.local.cache.PokemonMemoryCache
import com.example.pokemon.data.logger.PokemonLogger
import com.example.pokemon.data.remote.datasource.PokemonRemoteDataSource
import com.example.pokemon.data.remote.exceptions.PokemonException
import com.example.pokemon.data.remote.mappers.toDomain
import com.example.pokemon.data.remote.mappers.toPokemonFailure
import com.example.pokemon.domain.error.PokemonFailure
import com.example.pokemon.domain.model.Pokemon
import com.example.pokemon.domain.model.PokemonDetails
import com.example.pokemon.domain.model.PokemonResult
import com.example.pokemon.domain.repository.PokemonRepository

internal class PokemonRepositoryImpl(
    private val remote: PokemonRemoteDataSource,
    private val logger: PokemonLogger,
    private val memoryCache: PokemonMemoryCache
) : PokemonRepository {

    override suspend fun getPokemonList(): PokemonResult<List<Pokemon>> {
        return try {
            val pokemonDtos = remote.getPokemonList()
            memoryCache.setPokemons(pokemonDtos)
            PokemonResult.Success(
                pokemonDtos.map { it.toDomain() }
            )
        } catch (e: PokemonException) {
            logger.logError(e, "getPokemonList")
            PokemonResult.Error(e.toPokemonFailure())
        }
    }

    override suspend fun getPokemonDetails(name: String): PokemonResult<PokemonDetails> {
        return try {
            PokemonResult.Success(
                remote.getPokemonDetails(name).toDomain()
            )
        } catch (e: PokemonException) {
            logger.logError(e, "getPokemonDetails")
            PokemonResult.Error(e.toPokemonFailure())
        }
    }

    override suspend fun searchPokemon(query: String): PokemonResult<List<Pokemon>> {
        return try {
            val filtered = memoryCache
                .getPokemons()
                .filter {
                    it.name.contains(
                        other = query,
                        ignoreCase = true
                    )
                }
            PokemonResult.Success(
                filtered.map { it.toDomain() }
            )
        } catch (e: Exception) {
            logger.logError(e, "searchPokemon")
            PokemonResult.Error(PokemonFailure.Unknown)
        }
    }
}