package com.crleo.pokedex.di

import android.app.Activity
import android.content.Context
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.crleo.pokedex.MainActivity
import com.crleo.pokedex.data.network.PokedexApiService
import com.crleo.pokedex.router.NavControllerHolder
import com.crleo.pokedex.router.PokedexRouter
import com.crleo.pokedex.router.PokedexRouterImpl
import com.crleo.pokedex.ui.features.pokedex.data.PokedexRepository
import com.crleo.pokedex.ui.features.pokedex.data.PokedexRepositoryImpl
import com.crleo.pokedex.ui.features.pokedex.domain.PokedexInteractor
import com.crleo.pokedex.ui.features.pokedex.domain.PokedexInteractorImpl
import com.crleo.pokedex.ui.features.pokedex.presentation.PokedexPresenter
import com.crleo.pokedex.ui.features.pokedex.presentation.PokedexPresenterImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor) // Add the logging interceptor to the OkHttp client
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(PokedexApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideNavHostControllerHolder(): NavControllerHolder {
        return NavControllerHolder()
    }

    @Singleton
    @Provides
    fun providePokedexRouter(navControllerHolder: NavControllerHolder): PokedexRouter {
        return PokedexRouterImpl(navControllerHolder)
    }
}