package com.example.marveldatastone.screens.ShowAll.ShowAllComics

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marveldatastone.data.DataOrException
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
import com.example.marveldatastone.repository.MarvelDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowAllComicsViewModel @Inject constructor(private val repository: MarvelDataRepository) :
    ViewModel() {
    private val _comicsList= MutableStateFlow<List<ComicsData>>(emptyList())
    val comicsListVM = _comicsList.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getComicsFromDB().distinctUntilChanged().collect(){
                if(it.isNullOrEmpty())
                    Log.d("ShowAllComicsViewModel", "Empty DB")
                else
                    _comicsList.value=it
            }
        }
    }
}