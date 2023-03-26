package com.example.marveldatastone.screens.ShowAll.ShowAllComics.ShowAllHardCover

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marveldatastone.model.CharacterModels.HardCover.HardCoverData
import com.example.marveldatastone.repository.MarvelDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowAllHardCoverViewModel @Inject constructor(private val repository: MarvelDataRepository) :
    ViewModel() {
    private val _hardCover= MutableStateFlow<List<HardCoverData>>(emptyList())
    val hardCoverListVM = _hardCover.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getHardCoverfromDB().distinctUntilChanged().collect(){
                if(it.isNullOrEmpty())
                    Log.d("ShowAllComicsViewModel", "Empty DB")
                else
                    _hardCover.value=it
            }
        }
    }
}