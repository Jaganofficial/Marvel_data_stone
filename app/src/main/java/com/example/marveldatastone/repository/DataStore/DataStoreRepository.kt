package com.example.marveldatastone.repository.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DataStoreRepository (context: Context){
    private val dataStore: DataStore<Preferences> = context.createDataStore(name = "Favorites")
    private val favoritesDefault=""

    companion object{
        val PRF_FAVORITES= preferencesKey<String>("id")

        private var DATASTORE_INSTENCE: DataStoreRepository?=null

        fun getDataStoreInstence(context: Context):DataStoreRepository{
            return DATASTORE_INSTENCE?: synchronized(this)
            {
                DATASTORE_INSTENCE?.let {
                    return it
                }
                val dataStoreInstence=DataStoreRepository(context = context)
                DATASTORE_INSTENCE=dataStoreInstence
                dataStoreInstence
            }
        }
    }


    //setValue
    suspend fun setFavorites(id:String)
    {
        dataStore.edit {
            it[PRF_FAVORITES]=id
        }
    }

    suspend fun deleteFavorites(bookid: String,ID:String) {
        dataStore.edit {
            val index=ID.indexOf(bookid)
            if(index>=0)
            it[PRF_FAVORITES]=ID.removeRange(index,index+bookid.length+1)
        }
    }

    //getValue
    val getFavorites: Flow<String> = dataStore.data.map {
        it[PRF_FAVORITES]?:favoritesDefault
    }


}