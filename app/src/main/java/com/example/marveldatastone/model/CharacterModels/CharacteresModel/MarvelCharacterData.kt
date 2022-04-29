package com.example.marveldatastone.model.CharacterModels.CharacteresModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.marveldatastone.utils.Character_TypeConverters.*

@Entity(tableName = "Marvel_Character_Data")
@TypeConverters(
    ComicTypeConverter::class,
    DataTypeConverter::class,
   )
data class MarvelCharacterData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name="Data")
    val data: Data,
)