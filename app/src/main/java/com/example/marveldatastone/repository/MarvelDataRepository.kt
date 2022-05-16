package com.example.marveldatastone.repository


import com.example.marveldatastone.data.Characters.CharacterDao
import com.example.marveldatastone.data.ComicsDao.*
import com.example.marveldatastone.data.DataOrException
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.MarvelCharacterData
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
import com.example.marveldatastone.model.CharacterModels.Digest.DigestData
import com.example.marveldatastone.model.CharacterModels.GraphicNovel.GraphicNovelData
import com.example.marveldatastone.model.CharacterModels.HardCover.HardCoverData
import com.example.marveldatastone.model.CharacterModels.InfiniteNovel.InfiniteNovelData
import com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.TradePaperBookData
import com.example.marveldatastone.network.MarvelAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.Exception

class MarvelDataRepository @Inject constructor(private val api: MarvelAPI, private val characterDao: CharacterDao, private val comicsDao: Comics_Dao, private val digestDao: DigestDao,private val graphicNovelDao: GraphicNovelDao,private val hardCoverDao: HardCoverDao,private val infiniteNovelDao: InfiniteNovelDao,private val tradePaperBackDao: TradePaperBackDao){


    //get comic data from api
    suspend fun getComics(): DataOrException<ComicsData,Boolean,Exception>
    {
        val response = try {
            api.getComics()
        }
        catch (e:Exception)
        {
            return DataOrException(e=e)
        }
        comicsDao.deleteAllComicsData()
        comicsDao.insertComicsData(response)
        return DataOrException(data = response)
    }


    //get Trade PaperBook from api
    suspend fun getTradePaperBook(): DataOrException<TradePaperBookData,Boolean,Exception>
    {
        val response = try {
            api.getTradePaperBook()
        }
        catch (e:Exception)
        {
            return DataOrException(e=e)
        }
        tradePaperBackDao.deleteAllTradePaperBack()
        tradePaperBackDao.insertTradePaperBack(response)
        return DataOrException(data = response)
    }

    //get Infinite Comic from api
    suspend fun getInfiniteComic(): DataOrException<InfiniteNovelData,Boolean,Exception>
    {
        val response = try {
            api.getInfiniteComic()
        }
        catch (e:Exception)
        {
            return DataOrException(e=e)
        }
        infiniteNovelDao.deleteAllInfiniteNovel()
        infiniteNovelDao.insertInfiniteNovel(response)
        return DataOrException(data = response)
    }


    //get HardCover from api
    suspend fun getHardCover(): DataOrException<HardCoverData,Boolean,Exception>
    {
        val response = try {
            api.getHardCover()
        }
        catch (e:Exception)
        {
            return DataOrException(e=e)
        }
        hardCoverDao.deleteAllHardCover()
        hardCoverDao.insertHardCover(response)

        return DataOrException(data = response)
    }

    // get Digest from api
    suspend fun getDigest(): DataOrException<DigestData,Boolean,Exception>
    {
        val response = try {
            api.getDigest()
        }
        catch (e:Exception)
        {
            return DataOrException(e=e)
        }
        digestDao.deleteAllDigestData()
        digestDao.insertDigestData(response)
        return DataOrException(data = response)
    }

    //get GraphicNovel from api
    suspend fun getGraphicNovel(): DataOrException<GraphicNovelData,Boolean,Exception>
    {
        val response = try {
            api.getGraphicNovel()
        }
        catch (e:Exception)
        {
            return DataOrException(e=e)
        }
        graphicNovelDao.deleteAllGraphicNovel()
        graphicNovelDao.insertGraphicNovel(response)
        return DataOrException(data = response)
    }

    //Get Character data from api
    suspend fun getCharacter() : DataOrException<MarvelCharacterData,Boolean,Exception>
    {
        val response = try {
            api.getCharacter()
        }
        catch (e:Exception)
        {
            return DataOrException(e=e)
        }
        //characterDao.insertCharacter(response)
        return DataOrException(data = response)
    }

    //From DataBase
    fun getCharacterFromDB(): Flow<List<MarvelCharacterData>>
    {
        return characterDao.getCharacterDatafromDB().flowOn(Dispatchers.IO).conflate()
    }

    fun getComicsFromDB(): Flow<List<ComicsData>>
    {
        return comicsDao.getComicsDatafromDB().flowOn(Dispatchers.IO).conflate()
    }


    fun getTradePaperBookFromDB(): Flow<List<TradePaperBookData>>
    {
        return tradePaperBackDao.getTradePaperBackDatafromDB().flowOn(Dispatchers.IO).conflate()
    }

    suspend fun deleteTradePaperBack()
    {
        tradePaperBackDao.deleteAllTradePaperBack()
    }

    fun getHardCoverfromDB(): Flow<List<HardCoverData>>
    {
        return hardCoverDao.getHardCoverDatafromDB().flowOn(Dispatchers.IO).conflate()
    }

    suspend fun deleteHardCover()
    {
        hardCoverDao.deleteAllHardCover()
    }

    fun getDigestfromDB(): Flow<List<DigestData>>
    {
        return digestDao.getDigestDatafromDB().flowOn(Dispatchers.IO).conflate()
    }

    fun getGraphicNovelfromDB(): Flow<List<GraphicNovelData>>
    {
        return graphicNovelDao.getGraphicNovelDatafromDB().flowOn(Dispatchers.IO).conflate()
    }

    fun getInfiniteComicfromDB(): Flow<List<InfiniteNovelData>>
    {
        return infiniteNovelDao.getInfiniteNovelDatafromDB().flowOn(Dispatchers.IO).conflate()
    }

}