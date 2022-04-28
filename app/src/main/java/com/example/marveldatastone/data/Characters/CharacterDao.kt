package com.example.marveldatastone.data.Characters

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.MarvelCharacterData
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * from Marvel_Character_Data")
    fun getCharacterDatafromDB(): Flow<List<MarvelCharacterData>>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(marvelCharacterData: MarvelCharacterData)

    @Query("DELETE from Marvel_Character_Data")
    suspend fun deleteAllCharacter()
}
