package com.example.challenge_sword.di

import com.example.challenge_sword.data.repository.CatRepository
import com.example.challenge_sword.data.repository.CatRepositoryImpl
import com.example.challenge_sword.data.service.CatService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.thecatapi.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCatApi(retrofit: Retrofit) : CatService {
        return retrofit.create(CatService::class.java)
    }

    @Provides
    @Singleton
    fun provideCatRepository(catService: CatService): CatRepository {
        return CatRepositoryImpl(catService)
    }

}
