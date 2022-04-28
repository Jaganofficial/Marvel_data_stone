package com.example.marveldatastone.model.CharacterModels.CharacteresModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.marveldatastone.utils.Character_TypeConverters.*

@Entity(tableName = "Marvel_Character_Data")
@TypeConverters(
    ComicTypeConverter::class,
    DataTypeConverter::class,
    EventsTypeConverter::class,
    IntexListTypeConverter::class,
    ItemListTypeConverter::class,
    ItemListXXXTypeConverter::class,
    ItemXXListTypeConverter::class,
    ResultTypeConverter::class,
    SereiesTypeConverter::class,
    StoriesTypeConverter::class,
    ThumbnailTypeConverter::class,
    UrlListtypeConverter::class)
data class MarvelCharacterData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: Data,
    val etag: String,
    val status: String
)