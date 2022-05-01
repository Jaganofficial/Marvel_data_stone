package com.example.marveldatastone.data.ComicsDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
import com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.TradePaperBookData
import kotlinx.coroutines.flow.Flow

@Dao
interface HardCoverDao {
    @Query("SELECT * from HardCover_Data")
    fun getHardCoverDatafromDB(): Flow<List<TradePaperBookData>>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertHardCover(tradePaperBookData: TradePaperBookData)

    @Query("DELETE from HardCover_Data")
    suspend fun deleteAllHardCover()
}
