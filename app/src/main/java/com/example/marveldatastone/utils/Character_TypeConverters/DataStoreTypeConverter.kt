package com.example.marveldatastone.utils.Character_TypeConverters

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DataStoreTypeConverter{
    private val gson = Gson()
    private val type: Type = object : TypeToken<List<String>?>() {}.type

    fun stringToList(json: String?): List<String>? {
        return gson.fromJson<List<String>?>(json, type)
    }

    fun ListToString(data: List<String>?): String? {
        return gson.toJson(data, type)
    }
}