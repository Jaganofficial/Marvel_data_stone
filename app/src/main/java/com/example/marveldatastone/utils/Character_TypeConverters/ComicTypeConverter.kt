package com.example.marveldatastone.utils.Character_TypeConverters

import androidx.room.TypeConverter
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.Comics
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ComicTypeConverter {
    private val gson = Gson()
    private val type: Type = object : TypeToken<Comics?>() {}.type

    @TypeConverter
    fun stringToComics(json: String?): Comics? {
        return gson.fromJson<Comics?>(json, type)
    }

    @TypeConverter
    fun comicsToString(data: Comics): String? {
        return gson.toJson(data, type)
    }
}