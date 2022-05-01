package com.example.marveldatastone.data.ComicsDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
import com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.TradePaperBookData
import kotlinx.coroutines.flow.Flow

@Dao
interface GraphicNovelDao {
    @Query("SELECT * from Graphic_Novel_Data")
    fun getGraphicNovelDatafromDB(): Flow<List<TradePaperBookData>>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertGraphicNovel(tradePaperBookData: TradePaperBookData)

    @Query("DELETE from Graphic_Novel_Data")
    suspend fun deleteAllGraphicNovel()
}