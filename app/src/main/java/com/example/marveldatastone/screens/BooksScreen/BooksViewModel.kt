package com.example.marveldatastone.screens.BooksScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marveldatastone.data.DataOrException
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.MarvelCharacterData
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
import com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.TradePaperBookData
import com.example.marveldatastone.repository.MarvelDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BooksViewModel @Inject constructor(private val repository: MarvelDataRepository): ViewModel() {


    private val _tradePaperBookDataList= MutableStateFlow<List<TradePaperBookData>>(emptyList())
    val tradePaperBookDataList = _tradePaperBookDataList.asStateFlow()


    private val _infiniteNavelList= MutableStateFlow<List<TradePaperBookData>>(emptyList())
    val infiniteNavelList = _infiniteNavelList.asStateFlow()

    private val _hardCoverList= MutableStateFlow<List<TradePaperBookData>>(emptyList())
    val hardCoverList = _hardCoverList.asStateFlow()

    private val _digestList= MutableStateFlow<List<TradePaperBookData>>(emptyList())
    val digestList = _digestList.asStateFlow()


    private val _graphicNovelList= MutableStateFlow<List<TradePaperBookData>>(emptyList())
    val graphicNovelList = _graphicNovelList.asStateFlow()


    init {

        viewModelScope.launch(Dispatchers.IO) {
            repository.getInfiniteComicfromDB().distinctUntilChanged().collect() {
                if (it.isNullOrEmpty())
                    Log.d("BooksScreenViewModel", "Empty InfiniteComic DB")
                else
                    _infiniteNavelList.value = it
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTradePaperBookFromDB().distinctUntilChanged().collect(){
                if(it.isNullOrEmpty())
                    Log.d("BooksScreenViewModel", "Empty Trade paperbook DB")
                else
                    _tradePaperBookDataList.value=it
            }
        }




/*
        repository.getHardCoverfromDB().distinctUntilChanged().collect(){
            if(it.isNullOrEmpty())
                Log.d("BooksScreenViewModel", "Empty Trade paperbook DB")
            else
                _hardCoverList.value=it
        }

        repository.getDigestfromDB().distinctUntilChanged().collect(){
            if(it.isNullOrEmpty())
                Log.d("BooksScreenViewModel", "Empty Trade paperbook DB")
            else
                _digestList.value=it
        }

        repository.getGraphicNovelfromDB().distinctUntilChanged().collect(){
            if(it.isNullOrEmpty())
                Log.d("BooksScreenViewModel", "Empty Trade paperbook DB")
            else
                _graphicNovelList.value=it
        }

        */
    }


    suspend fun insertTradePaperBook(): DataOrException<TradePaperBookData, Boolean, Exception> {
      return repository.getTradePaperBook()
    }
    fun deleteTradePaperBack()=viewModelScope.launch {
        repository.deleteTradePaperBack()
    }



    //Get the Comics Data from repository
    suspend fun getTradePaperBook(): DataOrException<TradePaperBookData, Boolean, Exception> {
        return repository.getTradePaperBook()
    }

    suspend fun getInfiniteComic(): DataOrException<TradePaperBookData, Boolean, Exception> {
        return repository.getInfiniteComic()
    }


    suspend fun getHardCover(): DataOrException<TradePaperBookData, Boolean, Exception> {
        return repository.getHardCover()
    }
    suspend fun getDigest(): DataOrException<TradePaperBookData, Boolean, Exception> {
        return repository.getDigest()
    }
    suspend fun getGraphicNovel(): DataOrException<TradePaperBookData, Boolean, Exception> {
        return repository.getGraphicNovel()
    }


}