package com.example.marveldatastone.utils.Character_TypeConverters

import androidx.room.TypeConverter
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ResultTypeConverter {
    private val gson = Gson()
    private val type = object : TypeToken<List<Result?>?>() {}.type

    @TypeConverter
    fun stringToResult(json: String?): List<Result?>? {
        return gson.fromJson<List<Result?>>(json, type)
    }

    @TypeConverter
    fun resultToString(result: List<Result?>?): String? {
        return gson.toJson(result, type)
    }
}