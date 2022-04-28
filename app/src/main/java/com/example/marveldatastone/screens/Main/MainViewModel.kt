package com.example.marveldatastone.screens.Main

import androidx.lifecycle.ViewModel
import com.example.marveldatastone.data.Characters.CharacterDao
import com.example.marveldatastone.data.DataOrException
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.MarvelCharacterData
import com.example.marveldatastone.repository.MarvelDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class MainViewModel @Inject constructor(private val repository: MarvelDataRepository,private val characterDao: CharacterDao) :
    ViewModel() {

    //Get the Character Data
        suspend fun getCharacterData():DataOrException<MarvelCharacterData,Boolean,Exception> {
        return repository.getCharacter()
    }

}