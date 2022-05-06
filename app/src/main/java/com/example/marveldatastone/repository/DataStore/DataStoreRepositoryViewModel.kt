package com.example.marveldatastone.repository.DataStore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class DataStoreRepositoryViewModel (private val dataStoreRepository: DataStoreRepository):ViewModel() {
    private val _favorites=MutableLiveData("")
    val favorites: LiveData<String> = _favorites

    init {
        viewModelScope.launch {
            dataStoreRepository.getFavorites.collect{
                _favorites.value=it
            }
        }
    }

    suspend fun saveFavorite(id: String)
    {
        dataStoreRepository.setFavorites(id)
    }

}