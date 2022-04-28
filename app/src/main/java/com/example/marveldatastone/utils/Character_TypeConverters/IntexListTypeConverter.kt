package com.example.marveldatastone.utils.Character_TypeConverters

import androidx.room.TypeConverter
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.ItemX
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IntexListTypeConverter {
    private val gson = Gson()
    private val type = object : TypeToken<List<ItemX?>?>() {}.type

    @TypeConverter
    fun stringToResult(json: String?): List<ItemX?>? {
        return gson.fromJson<List<ItemX?>>(json, type)
    }

    @TypeConverter
    fun resultToString(result: List<ItemX?>?): String? {
        return gson.toJson(result, type)
    }
}