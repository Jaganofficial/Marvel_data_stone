package com.example.marveldatastone.model.CharacterModels.HardCover

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.marveldatastone.utils.Character_TypeConverters.HardCoverDataTypeConverter


@Entity(tableName = "HardCover_Data")
@TypeConverters(
    HardCoverDataTypeConverter::class
)

data class HardCoverData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name="Data")
    val data: Data
)