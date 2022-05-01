package com.example.marveldatastone.model.CharacterModels.GraphicNovel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

import com.example.marveldatastone.utils.Character_TypeConverters.GraphicNovelDataTypeConverter

@Entity(tableName = "Graphic_Novel_Data")
@TypeConverters(
    GraphicNovelDataTypeConverter::class
)

data class GraphicNovelData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name="Data")
    val data: Data
)