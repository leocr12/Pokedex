package com.crleo.pokedex.di

import android.app.Activity
import androidx.lifecycle.lifecycleScope
import com.crleo.pokedex.MainActivity
import com.crleo.pokedex.data.network.PokedexApiService
import com.crleo.pokedex.ui.features.pokedex.data.PokedexRepository
import com.crleo.pokedex.ui.features.pokedex.data.PokedexRepositoryImpl
import com.crleo.pokedex.ui.features.pokedex.domain.PokedexInteractor
import com.crleo.pokedex.ui.features.pokedex.domain.PokedexInteractorImpl
import com.crleo.pokedex.ui.features.pokedex.presentation.PokedexPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providePokedexApiService(): PokedexApiService {
        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(PokedexApiService::class.java)
    }

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
    fun providePokedexPresenter(interactor: PokedexInteractor): PokedexPresenter {
        return PokedexPresenter(interactor)
    }
}