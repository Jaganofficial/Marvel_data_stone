package com.example.marveldatastone.utils.Character_TypeConverters

import androidx.room.TypeConverter
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ItemListTypeConverter {
    private val gson = Gson()
    private val type = object : TypeToken<List<Item?>?>() {}.type

    @TypeConverter
    fun stringToResult(json: String?): List<Item?>? {
        return gson.fromJson<List<Item?>>(json, type)
    }

    @TypeConverter
    fun resultToString(result: List<Item?>?): String? {
        return gson.toJson(result, type)
    }
}