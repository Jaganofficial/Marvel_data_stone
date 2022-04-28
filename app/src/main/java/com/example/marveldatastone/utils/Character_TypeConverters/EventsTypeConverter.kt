package com.example.marveldatastone.utils.Character_TypeConverters

import androidx.room.TypeConverter
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.Events
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class EventsTypeConverter {
    private val gson = Gson()
    private val type: Type = object : TypeToken<Events?>() {}.type

    @TypeConverter
    fun stringToData(json: String?): Events? {
        return gson.fromJson<Events?>(json, type)
    }

    @TypeConverter
    fun dataToString(data: Events?): String? {
        return gson.toJson(data, type)
    }
}