package com.example.marveldatastone.data.ComicsDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
import com.example.marveldatastone.model.CharacterModels.InfiniteNovel.InfiniteNovelData
import com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.TradePaperBookData
import kotlinx.coroutines.flow.Flow

@Dao
interface InfiniteNovelDao {
    @Query("SELECT * from Infinite_Novel_Data")
    fun getInfiniteNovelDatafromDB(): Flow<List<InfiniteNovelData>>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertInfiniteNovel(infiniteNovelData: InfiniteNovelData)

    @Query("DELETE from Infinite_Novel_Data")
    suspend fun deleteAllInfiniteNovel()
}