package com.example.marveldatastone.model.CharacterModels.ComicsModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.marveldatastone.model.CharacterModels.ComicsModels.Data
import com.example.marveldatastone.utils.Character_TypeConverters.*
import com.example.marveldatastone.utils.Character_TypeConverters.DataTypeConverter


@Entity(tableName = "Comics_Data")
@TypeConverters(
    ComicTypeConverter::class,
    DatafromComicsTypeConverter::class,
)

data class ComicsData(

    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @ColumnInfo(name="Data")
    val data: Data

)