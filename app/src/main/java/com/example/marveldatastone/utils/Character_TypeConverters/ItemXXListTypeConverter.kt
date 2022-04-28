package com.example.marveldatastone.utils.Character_TypeConverters

import androidx.room.TypeConverter
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.ItemXX
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ItemXXListTypeConverter {
    private val gson = Gson()
    private val type = object : TypeToken<List<ItemXX?>?>() {}.type

    @TypeConverter
    fun stringToResult(json: String?): List<ItemXX?>? {
        return gson.fromJson<List<ItemXX?>>(json, type)
    }

    @TypeConverter
    fun resultToString(result: List<ItemXX?>?): String? {
        return gson.toJson(result, type)
    }
}