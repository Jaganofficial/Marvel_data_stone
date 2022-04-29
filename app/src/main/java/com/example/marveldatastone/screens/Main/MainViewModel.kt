package com.example.marveldatastone.screens.Main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marveldatastone.data.Characters.CharacterDao
import com.example.marveldatastone.data.DataOrException
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.MarvelCharacterData
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
import com.example.marveldatastone.repository.MarvelDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class MainViewModel @Inject constructor(private val repository: MarvelDataRepository) :
    ViewModel() {

    private val _characterList= MutableStateFlow<List<MarvelCharacterData>>(emptyList())
    val characterList = _characterList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCharacterFromDB().distinctUntilChanged().collect(){
                if(it.isNullOrEmpty())
                    Log.d("MainScreenViewModel", "Empty DB")
                else
                    _characterList.value=it
            }
        }
    }

    //Get the Character Data from repository
        suspend fun getCharacterData():DataOrException<MarvelCharacterData,Boolean,Exception> {
        return repository.getCharacter()
    }

    //Get the Comics Data from repository
    suspend fun getComics(): DataOrException<ComicsData,Boolean,Exception>{
        return repository.getComics()
    }

}