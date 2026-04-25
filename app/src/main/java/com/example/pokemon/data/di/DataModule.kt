package com.example.pokemon.data.di

import com.example.pokemon.data.logger.PokemonLogger
import com.example.pokemon.data.logger.PokemonLoggerImpl
import com.example.pokemon.data.remote.datasource.PokemonRemoteDataSource
import com.example.pokemon.data.repository.PokemonRepositoryImpl
import com.example.pokemon.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providePokemonLogger(): PokemonLogger {
        return PokemonLoggerImpl()
    }

    @Provides
    @Singleton
    fun providePokemonRepository(
        remote: PokemonRemoteDataSource,
        logger: PokemonLogger
    ): PokemonRepository {
        return PokemonRepositoryImpl(remote, logger)
    }
}