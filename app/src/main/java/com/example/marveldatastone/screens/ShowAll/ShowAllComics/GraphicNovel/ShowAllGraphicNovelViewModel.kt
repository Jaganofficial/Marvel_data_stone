package com.example.marveldatastone.screens.ShowAll.ShowAllComics.GraphicNovel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marveldatastone.model.CharacterModels.GraphicNovel.GraphicNovelData
import com.example.marveldatastone.model.CharacterModels.InfiniteNovel.InfiniteNovelData
import com.example.marveldatastone.repository.MarvelDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowAllGraphicNovelViewModel @Inject constructor(private val repository: MarvelDataRepository) :
    ViewModel()  {
    private val _graphicNovel= MutableStateFlow<List<GraphicNovelData>>(emptyList())
    val graphicNovelListVM = _graphicNovel.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getGraphicNovelfromDB().distinctUntilChanged().collect(){
                if(it.isNullOrEmpty())
                    Log.d("ShowAllComicsViewModel", "Empty DB")
                else
                    _graphicNovel.value=it
            }
        }
    }
}
