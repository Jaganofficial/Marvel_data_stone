package com.example.marveldatastone.screens.ShowAll.ShowAllComics.ShowAlltradePaperback

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marveldatastone.model.CharacterModels.HardCover.HardCoverData
import com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.TradePaperBookData
import com.example.marveldatastone.repository.MarvelDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ShowAllTradePaperBackViewModel@Inject constructor(private val repository: MarvelDataRepository) :
    ViewModel() {
    private val _tradePaperBack= MutableStateFlow<List<TradePaperBookData>>(emptyList())
    val tradePaperBackListVM = _tradePaperBack.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTradePaperBookFromDB().distinctUntilChanged().collect(){
                if(it.isNullOrEmpty())
                    Log.d("ShowAllComicsViewModel", "Empty DB")
                else
                    _tradePaperBack.value=it
            }
        }
    }
}