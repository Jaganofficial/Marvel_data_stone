package com.example.marveldatastone.data.ComicsDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
import com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.TradePaperBookData
import kotlinx.coroutines.flow.Flow

@Dao
interface DigestDao {
    @Query("SELECT * from Digest_Data")
    fun getDigestDatafromDB(): Flow<List<TradePaperBookData>>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertDigestData(tradePaperBookData: TradePaperBookData)

    @Query("DELETE from Digest_Data")
    suspend fun deleteAllDigestData()
}