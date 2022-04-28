package com.example.marveldatastone.utils.Character_TypeConverters

import androidx.room.TypeConverter
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.Series
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SereiesTypeConverter {
    private val gson = Gson()
    private val type = object : TypeToken<Series?>() {}.type

    @TypeConverter
    fun stringToSeries(json: String?): Series? {
        return gson.fromJson<Series?>(json, type)
    }

    @TypeConverter
    fun seriesToString(result: Series?): String? {
        return gson.toJson(result, type)
    }
}