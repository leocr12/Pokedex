package com.crleo.pokedex.di

import com.crleo.pokedex.data.network.PokedexApiService
import com.crleo.pokedex.router.PokedexRouter
import com.crleo.pokedex.ui.features.details.data.PokemonDetailsRepository
import com.crleo.pokedex.ui.features.details.data.PokemonDetailsRepositoryImpl
import com.crleo.pokedex.ui.features.details.domain.PokemonDetailsInteractor
import com.crleo.pokedex.ui.features.details.domain.PokemonDetailsInteractorImpl
import com.crleo.pokedex.ui.features.details.presentation.PokemonDetailsPresenter
import com.crleo.pokedex.ui.features.details.presentation.PokemonDetailsPresenterImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonDetailsModule {
    @Singleton
    @Provides
    fun provideRepository(pokedexApiService: PokedexApiService): PokemonDetailsRepository {
        return PokemonDetailsRepositoryImpl(pokedexApiService)
    }

    @Singleton
    @Provides
    fun provideInteractor(repository: PokemonDetailsRepository): PokemonDetailsInteractor {
        return PokemonDetailsInteractorImpl(repository)
    }

    @Singleton
    @Provides
    fun providePresenter(interactor: PokemonDetailsInteractor, router: PokedexRouter): PokemonDetailsPresenter {
        return PokemonDetailsPresenterImpl(interactor)
    }
}