package com.example.marveldatastone.screens.ShowAll.ShowAllComics.InfiniteNovelShowAll

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
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
class ShowAllInfiniteNovelViewModel @Inject constructor(private val repository: MarvelDataRepository) :
    ViewModel()  {
    private val _infiniteNovel= MutableStateFlow<List<InfiniteNovelData>>(emptyList())
    val infiniteNovelListVM = _infiniteNovel.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getInfiniteComicfromDB().distinctUntilChanged().collect(){
                if(it.isNullOrEmpty())
                    Log.d("ShowAllComicsViewModel", "Empty DB")
                else
                    _infiniteNovel.value=it
            }
        }
    }
}