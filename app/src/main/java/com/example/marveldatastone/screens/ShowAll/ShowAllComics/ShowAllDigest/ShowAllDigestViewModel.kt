package com.example.marveldatastone.screens.ShowAll.ShowAllComics.ShowAllDigest

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marveldatastone.model.CharacterModels.Digest.DigestData
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
class ShowAllDigestViewModel @Inject constructor(private val repository: MarvelDataRepository) :
    ViewModel() {

    private val _digest= MutableStateFlow<List<DigestData>>(emptyList())
    val digestListVM = _digest.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getDigestfromDB().distinctUntilChanged().collect(){
                if(it.isNullOrEmpty())
                    Log.d("ShowAllComicsViewModel", "Empty DB")
                else
                    _digest.value=it
            }
        }
    }

}