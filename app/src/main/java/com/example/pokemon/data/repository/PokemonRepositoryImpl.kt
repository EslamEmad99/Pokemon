package com.example.pokemon.data.repository

import com.example.pokemon.data.logger.PokemonLogger
import com.example.pokemon.data.remote.datasource.PokemonRemoteDataSource
import com.example.pokemon.data.remote.exceptions.PokemonException
import com.example.pokemon.data.remote.mappers.toDomain
import com.example.pokemon.data.remote.mappers.toPokemonFailure
import com.example.pokemon.domain.model.Pokemon
import com.example.pokemon.domain.model.PokemonDetails
import com.example.pokemon.domain.model.PokemonResult
import com.example.pokemon.domain.repository.PokemonRepository

internal class PokemonRepositoryImpl(
    private val remote: PokemonRemoteDataSource,
    private val logger: PokemonLogger
) : PokemonRepository {

    override suspend fun getPokemonList(): PokemonResult<List<Pokemon>> {
        return try {
            PokemonResult.Success(
                remote.getPokemonList().map { it.toDomain() }
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
}