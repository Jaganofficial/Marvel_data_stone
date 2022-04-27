package com.example.marveldatastone.screens.Main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marveldatastone.data.DataOrException
import com.example.marveldatastone.model.MarvelCharacterData
import com.example.marveldatastone.repository.MarvelDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class MainViewModel @Inject constructor(private val repository: MarvelDataRepository) :
    ViewModel() {

    //Get the Character Data
        suspend fun getCharacterData():DataOrException<MarvelCharacterData,Boolean,Exception> {
        return repository.getCharacter()
    }
}