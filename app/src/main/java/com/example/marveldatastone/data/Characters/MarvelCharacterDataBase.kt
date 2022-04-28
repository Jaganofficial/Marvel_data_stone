package com.example.marveldatastone.data.Characters

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.MarvelCharacterData

@Database(entities = [MarvelCharacterData::class], version = 1, exportSchema = false)
abstract class MarvelCharacterDataBase : RoomDatabase() {
    abstract fun marvelCharactereDao() : CharacterDao
}