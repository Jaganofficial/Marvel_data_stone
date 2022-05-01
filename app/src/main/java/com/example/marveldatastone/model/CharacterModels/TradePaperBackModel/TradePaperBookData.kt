package com.example.marveldatastone.model.CharacterModels.TradePaperBackModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.*
import com.example.marveldatastone.utils.Character_TypeConverters.*


@Entity(tableName = "Trade_PaperBook_Data")
@TypeConverters(
    ComicTypeConverter::class,
    DatafromTradePaperBookTypeConverter::class
)

data class TradePaperBookData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name="Data")
    val data: Data
)