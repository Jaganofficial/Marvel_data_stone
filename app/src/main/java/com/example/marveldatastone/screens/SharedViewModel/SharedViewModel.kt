package com.example.marveldatastone.screens.SharedViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.marveldatastone.model.CharacterModels.HardCover.Result

class SharedViewModel:ViewModel() {

    var hardCoverResult by mutableStateOf<Result?>(null)
    private set

    fun addHardCoverResult(newHardCoverResult: Result)
    {
        infiniteNovelResult=null
        tradepaperbackResult=null
        digestResult=null
        graphicNovelResult=null
        comicsResult=null
        hardCoverResult=newHardCoverResult
    }

    var infiniteNovelResult by mutableStateOf<com.example.marveldatastone.model.CharacterModels.InfiniteNovel.Result?>(null)
        private set

    fun addInfiniteNovelResult(_infiniteNovelResult: com.example.marveldatastone.model.CharacterModels.InfiniteNovel.Result)
    {
        hardCoverResult=null
        tradepaperbackResult=null
        digestResult=null
        graphicNovelResult=null
        comicsResult=null
        infiniteNovelResult=_infiniteNovelResult
    }

    var tradepaperbackResult by mutableStateOf<com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.Result?>(null)
        private set

    fun addTradepaperbackResult(_tradepaperbackResult: com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.Result)
    {
        hardCoverResult=null
        infiniteNovelResult=null
        digestResult=null
        graphicNovelResult=null
        comicsResult=null
        tradepaperbackResult=_tradepaperbackResult
    }

    var digestResult by mutableStateOf<com.example.marveldatastone.model.CharacterModels.Digest.Result?>(null)
        private set

    fun addDigestResult(_digestResult: com.example.marveldatastone.model.CharacterModels.Digest.Result)
    {
        hardCoverResult=null
        infiniteNovelResult=null
        tradepaperbackResult=null
        graphicNovelResult=null
        comicsResult=null
        digestResult=_digestResult
    }


    var graphicNovelResult by mutableStateOf<com.example.marveldatastone.model.CharacterModels.GraphicNovel.Result?>(null)
        private set

    fun addGraphicNovelResult(_graphicNovelResult: com.example.marveldatastone.model.CharacterModels.GraphicNovel.Result)
    {
        hardCoverResult=null
        infiniteNovelResult=null
        tradepaperbackResult=null
        digestResult=null
        comicsResult=null
        graphicNovelResult=_graphicNovelResult
    }

    var comicsResult by mutableStateOf<com.example.marveldatastone.model.CharacterModels.ComicsModels.Result?>(null)
        private set

    fun addComicsResult(_comicsResult: com.example.marveldatastone.model.CharacterModels.ComicsModels.Result)
    {
        hardCoverResult=null
        infiniteNovelResult=null
        tradepaperbackResult=null
        digestResult=null
        graphicNovelResult=null
        comicsResult=_comicsResult
    }



}