package com.example.marveldatastone.utils.Character_TypeConverters

import androidx.room.TypeConverter
import com.example.marveldatastone.model.CharacterModels.HardCover.Data
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class HardCoverDataTypeConverter {
    private val gson = Gson()
    private val type: Type = object : TypeToken<Data?>() {}.type

    @TypeConverter
    fun stringToData(json: String?): Data? {
        return gson.fromJson<Data?>(json, type)
    }

    @TypeConverter
    fun dataToString(data: Data?): String? {
        return gson.toJson(data, type)
    }
}