package com.example.marveldatastone.network

import com.example.marveldatastone.model.CharacterModels.CharacteresModel.MarvelCharacterData
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
import com.example.marveldatastone.model.CharacterModels.Digest.DigestData
import com.example.marveldatastone.model.CharacterModels.GraphicNovel.GraphicNovelData
import com.example.marveldatastone.model.CharacterModels.HardCover.HardCoverData
import com.example.marveldatastone.model.CharacterModels.InfiniteNovel.InfiniteNovelData
import com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.TradePaperBookData
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

    @GET("comics")
    suspend fun getTradePaperBook(
        @Query("format")formate:String="trade paperback",
        @Query("orderBy")orderBy:String="-modified",
        @Query("limit") limit:String="100",
        @Query("ts")ts:String="1",
        @Query("apikey")apikey:String=Constents.API_KEY,
        @Query("hash")hash:String=Constents.HASH_VALUE
    ) : TradePaperBookData

    @GET("comics")
    suspend fun getHardCover(
        @Query("format")formate:String="hardcover",
        @Query("orderBy")orderBy:String="-onsaleDate",
        @Query("limit") limit:String="100",
        @Query("ts")ts:String="1",
        @Query("apikey")apikey:String=Constents.API_KEY,
        @Query("hash")hash:String=Constents.HASH_VALUE
    ) : HardCoverData

    @GET("comics")
    suspend fun getDigest(
        @Query("format")formate:String="digest",
        @Query("orderBy")orderBy:String="-modified",
        @Query("limit") limit:String="100",
        @Query("ts")ts:String="1",
        @Query("apikey")apikey:String=Constents.API_KEY,
        @Query("hash")hash:String=Constents.HASH_VALUE
    ) : DigestData

    @GET("comics")
    suspend fun getGraphicNovel(
        @Query("format")formate:String="graphic novel",
        @Query("orderBy")orderBy:String="-modified",
        @Query("limit") limit:String="100",
        @Query("ts")ts:String="1",
        @Query("apikey")apikey:String=Constents.API_KEY,
        @Query("hash")hash:String=Constents.HASH_VALUE
    ) : GraphicNovelData

    @GET("comics")
    suspend fun getInfiniteComic(
        @Query("format")formate:String="infinite comic",
        @Query("orderBy")orderBy:String="-modified",
        @Query("limit") limit:String="100",
        @Query("ts")ts:String="1",
        @Query("apikey")apikey:String=Constents.API_KEY,
        @Query("hash")hash:String=Constents.HASH_VALUE
    ) : InfiniteNovelData

}