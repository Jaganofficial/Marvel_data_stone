package com.example.marveldatastone.utils.Character_TypeConverters

import androidx.room.TypeConverter
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.Thumbnail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ThumbnailTypeConverter {
    private val gson = Gson()
    private val type: Type = object : TypeToken<Thumbnail?>() {}.type

    @TypeConverter
    fun stringToThumbnail(json: String?): Thumbnail? {
        return gson.fromJson<Thumbnail?>(json, type)
    }

    @TypeConverter
    fun thumbnailToString(thumbnail: Thumbnail?): String? {
        return gson.toJson(thumbnail, type)
    }
}