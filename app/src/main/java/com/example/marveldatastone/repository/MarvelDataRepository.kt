package com.example.marveldatastone.repository

import com.example.marveldatastone.data.Characters.CharacterDao
import com.example.marveldatastone.data.DataOrException
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.MarvelCharacterData
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
import com.example.marveldatastone.network.MarvelAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
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

    suspend fun getComics(): DataOrException<ComicsData,Boolean,Exception>
    {
        val response = try {
            api.getComics()
        }
        catch (e:Exception)
        {
            return DataOrException(e=e)
        }
        return DataOrException(data = response)
    }

    fun getCharacterFromDB(): Flow<List<MarvelCharacterData>>
    {
        return characterDao.getCharacterDatafromDB().flowOn(Dispatchers.IO).conflate()
    }

}