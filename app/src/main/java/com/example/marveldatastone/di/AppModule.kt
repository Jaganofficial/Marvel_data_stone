package com.example.marveldatastone.di

import com.example.marveldatastone.network.MarvelAPI
import com.example.marveldatastone.utils.Constents
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOpenMarvelAPI():MarvelAPI
    {
        return Retrofit.Builder().baseUrl(Constents.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(MarvelAPI::class.java)
    }
}