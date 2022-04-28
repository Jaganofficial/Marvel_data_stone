package com.example.marveldatastone.utils.Character_TypeConverters

import androidx.room.TypeConverter
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.Stories
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StoriesTypeConverter {
    private val gson = Gson()
    private val type = object : TypeToken<Stories?>() {}.type

    @TypeConverter
    fun stringToStories(json: String?): Stories? {
        return gson.fromJson<Stories?>(json, type)
    }

    @TypeConverter
    fun storiesToString(result: Stories?): String? {
        return gson.toJson(result, type)
    }
}