package com.example.marveldatastone.repository

import com.example.marveldatastone.data.Characters.CharacterDao
import com.example.marveldatastone.data.DataOrException
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.MarvelCharacterData
import com.example.marveldatastone.network.MarvelAPI
import javax.inject.Inject
import kotlin.Exception

class MarvelDataRepository @Inject constructor(private val api: MarvelAPI,private val characterDao: CharacterDao){
    //Get Character data
    suspend fun getCharacter() : DataOrException<MarvelCharacterData,Boolean,Exception>
    {
        val response = try {
            api.getCharacter()
        }
        catch (e:Exception)
        {
            return DataOrException(e=e)
        }
        characterDao.insertCharacter(response)
        return DataOrException(data = response)
    }
}