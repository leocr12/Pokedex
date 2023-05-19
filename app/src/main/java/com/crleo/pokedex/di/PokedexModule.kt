package com.crleo.pokedex.di

import com.crleo.pokedex.data.network.PokedexApiService
import com.crleo.pokedex.router.PokedexRouter
import com.crleo.pokedex.ui.features.pokedex.data.PokedexRepository
import com.crleo.pokedex.ui.features.pokedex.data.PokedexRepositoryImpl
import com.crleo.pokedex.ui.features.pokedex.domain.PokedexInteractor
import com.crleo.pokedex.ui.features.pokedex.domain.PokedexInteractorImpl
import com.crleo.pokedex.ui.features.pokedex.presentation.PokedexPresenter
import com.crleo.pokedex.ui.features.pokedex.presentation.PokedexPresenterImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokedexModule {
    @Singleton
    @Provides
    fun providePokedexRepository(pokedexApiService: PokedexApiService): PokedexRepository {
        return PokedexRepositoryImpl(pokedexApiService)
    }

    @Singleton
    @Provides
    fun providePokedexInteractor(repository: PokedexRepository): PokedexInteractor {
        return PokedexInteractorImpl(repository)
    }

    @Singleton
    @Provides
    fun providePokedexPresenter(interactor: PokedexInteractor, router: PokedexRouter): PokedexPresenter {
        return PokedexPresenterImpl(interactor, router)
    }
}