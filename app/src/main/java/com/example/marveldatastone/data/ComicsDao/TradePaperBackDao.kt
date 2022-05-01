package com.example.marveldatastone.data.ComicsDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
import com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.TradePaperBookData
import kotlinx.coroutines.flow.Flow

@Dao
interface TradePaperBackDao {
    @Query("SELECT * from Trade_PaperBook_Data")
    fun getTradePaperBackDatafromDB(): Flow<List<TradePaperBookData>>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertTradePaperBack(tradePaperBookData: TradePaperBookData)

    @Query("DELETE from Trade_PaperBook_Data")
    suspend fun deleteAllTradePaperBack()
}
