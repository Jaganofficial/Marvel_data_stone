package com.example.marveldatastone.network

import com.example.marveldatastone.model.CharacterModels.CharacteresModel.MarvelCharacterData
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
import com.example.marveldatastone.utils.Constents
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface MarvelAPI {

    @GET("characters")
    suspend fun getCharacter(
        @Query("orderBy")orderBy:String="-modified",
        @Query("limit") limit:String="100",
        @Query("ts")ts:String="1",
        @Query("apikey")apikey:String=Constents.API_KEY,
        @Query("hash")hash:String=Constents.HASH_VALUE
    ) : MarvelCharacterData

    @GET("comics")
    suspend fun getComics(
        @Query("format")formate:String="comic",
        @Query("orderBy")orderBy:String="-modified",
        @Query("limit") limit:String="100",
        @Query("ts")ts:String="1",
        @Query("apikey")apikey:String=Constents.API_KEY,
        @Query("hash")hash:String=Constents.HASH_VALUE
    ) : ComicsData


}