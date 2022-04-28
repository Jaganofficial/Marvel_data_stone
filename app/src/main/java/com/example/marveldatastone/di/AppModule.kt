package com.example.marveldatastone.di

import android.content.Context
import androidx.room.Room
import com.example.marveldatastone.data.Characters.CharacterDao
import com.example.marveldatastone.data.Characters.MarvelCharacterDataBase
import com.example.marveldatastone.network.MarvelAPI
import com.example.marveldatastone.utils.Constents
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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


    //For DB
    @Singleton
    @Provides
    fun provideCharacterDao(marvelCharacterDataBase: MarvelCharacterDataBase): CharacterDao =marvelCharacterDataBase.marvelCharactereDao()

    @Singleton
    @Provides
    fun createCharacterDatabase(@ApplicationContext context: Context): MarvelCharacterDataBase =
        Room.databaseBuilder(context, MarvelCharacterDataBase::class.java,"Character_Database").fallbackToDestructiveMigration().build()
}