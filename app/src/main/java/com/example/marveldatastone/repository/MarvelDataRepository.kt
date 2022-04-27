package com.example.marveldatastone.repository

import com.example.marveldatastone.data.DataOrException
import com.example.marveldatastone.model.MarvelCharacterData
import com.example.marveldatastone.network.MarvelAPI
import javax.inject.Inject
import kotlin.Exception

class MarvelDataRepository @Inject constructor(private val api: MarvelAPI){

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
        return DataOrException(data = response)
    }
}