package com.example.marveldatastone.data.Characters

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marveldatastone.data.ComicsDao.*
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.MarvelCharacterData
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
import com.example.marveldatastone.model.CharacterModels.Digest.DigestData
import com.example.marveldatastone.model.CharacterModels.GraphicNovel.GraphicNovelData
import com.example.marveldatastone.model.CharacterModels.HardCover.HardCoverData
import com.example.marveldatastone.model.CharacterModels.InfiniteNovel.InfiniteNovelData
import com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.TradePaperBookData

@Database(entities = [MarvelCharacterData::class,ComicsData::class, DigestData::class, GraphicNovelData::class, HardCoverData::class, InfiniteNovelData::class,TradePaperBookData::class], version = 3, exportSchema = false)
abstract class MarvelCharacterDataBase : RoomDatabase() {
    abstract fun marvelCharactereDao() : CharacterDao
    abstract fun marvelComicsDao(): Comics_Dao
    abstract fun marvelDigestDao(): DigestDao
    abstract fun marvelGraphicNovelDao(): GraphicNovelDao
    abstract fun marvelHardCoverDao(): HardCoverDao
    abstract fun marvelInfiniteNovelDao(): InfiniteNovelDao
    abstract fun marvelTradePaperBackDao(): TradePaperBackDao
}