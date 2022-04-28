package com.example.marveldatastone.utils.Character_TypeConverters

import androidx.room.TypeConverter
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.Url
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UrlListtypeConverter {
    private val gson = Gson()
    private val type = object : TypeToken<List<Url?>?>() {}.type

    @TypeConverter
    fun stringToResult(json: String?): List<Url?>? {
        return gson.fromJson<List<Url?>>(json, type)
    }

    @TypeConverter
    fun resultToString(result: List<Url?>?): String? {
        return gson.toJson(result, type)
    }
}