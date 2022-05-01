package com.example.marveldatastone.model.CharacterModels.Digest

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.Data
import com.example.marveldatastone.utils.Character_TypeConverters.*
@Entity(tableName = "Digest_Data")
@TypeConverters(
    ComicTypeConverter::class,
    DatafromTradePaperBookTypeConverter::class
)

data class DigestData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name="Data")
    val data: Data
)
