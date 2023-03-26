package com.example.marveldatastone.di

import android.content.Context
import androidx.room.Room
import com.example.marveldatastone.data.Characters.CharacterDao
import com.example.marveldatastone.data.Characters.MarvelCharacterDataBase
import com.example.marveldatastone.data.ComicsDao.*
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


    //MarvelAPI Provider
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

    //Comics Provider
    @Singleton
    @Provides
    fun provideComicsDao(marvelCharacterDataBase: MarvelCharacterDataBase): Comics_Dao =marvelCharacterDataBase.marvelComicsDao()

    //Digest Provider
    @Singleton
    @Provides
    fun provideDigestDao(marvelCharacterDataBase: MarvelCharacterDataBase): DigestDao =marvelCharacterDataBase.marvelDigestDao()

    //GraphicNovel Provider
    @Singleton
    @Provides
    fun provideGraphicNovelDao(marvelCharacterDataBase: MarvelCharacterDataBase): GraphicNovelDao =marvelCharacterDataBase.marvelGraphicNovelDao()

    //HardCover Provider
    @Singleton
    @Provides
    fun provideHardCoverDao(marvelCharacterDataBase: MarvelCharacterDataBase): HardCoverDao =marvelCharacterDataBase.marvelHardCoverDao()

    //InfiniteNovel Provider
    @Singleton
    @Provides
    fun provideInfiniteNovelDao(marvelCharacterDataBase: MarvelCharacterDataBase): InfiniteNovelDao =marvelCharacterDataBase.marvelInfiniteNovelDao()

    //TradePaperBack Provider
    @Singleton
    @Provides
    fun provideTradePaperBackDao(marvelCharacterDataBase: MarvelCharacterDataBase): TradePaperBackDao =marvelCharacterDataBase.marvelTradePaperBackDao()


    @Singleton
    @Provides
    fun createCharacterDatabase(@ApplicationContext context: Context): MarvelCharacterDataBase =
        Room.databaseBuilder(context, MarvelCharacterDataBase::class.java,"Character_Database").fallbackToDestructiveMigration().build()

}