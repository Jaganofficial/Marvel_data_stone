package com.example.marveldatastone.data.ComicsDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.MarvelCharacterData
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
import kotlinx.coroutines.flow.Flow

@Dao
interface Comics_Dao {
    @Query("SELECT * from Comics_Data")
    fun getComicsDatafromDB(): Flow<List<ComicsData>>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertComicsData(comicsData: ComicsData)

    @Query("DELETE from Comics_Data")
    suspend fun deleteAllComicsData()
}