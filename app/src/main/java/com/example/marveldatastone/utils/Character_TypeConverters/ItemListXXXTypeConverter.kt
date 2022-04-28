package com.example.marveldatastone.utils.Character_TypeConverters

import androidx.room.TypeConverter
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.ItemXXX
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ItemListXXXTypeConverter {
    private val gson = Gson()
    private val type = object : TypeToken<List<ItemXXX?>?>() {}.type

    @TypeConverter
    fun stringToResult(json: String?): List<ItemXXX?>? {
        return gson.fromJson<List<ItemXXX?>>(json, type)
    }

    @TypeConverter
    fun resultToString(result: List<ItemXXX?>?): String? {
        return gson.toJson(result, type)
    }
}