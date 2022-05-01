package com.example.marveldatastone.data.ComicsDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
import com.example.marveldatastone.model.CharacterModels.Digest.DigestData
import com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.TradePaperBookData
import kotlinx.coroutines.flow.Flow

@Dao
interface DigestDao {
    @Query("SELECT * from Digest_Data")
    fun getDigestDatafromDB(): Flow<List<DigestData>>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertDigestData(digestData: DigestData)

    @Query("DELETE from Digest_Data")
    suspend fun deleteAllDigestData()
}