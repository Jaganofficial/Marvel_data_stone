package com.example.marveldatastone.screens.SharedViewModel

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter
import com.example.marveldatastone.model.CharacterModels.HardCover.Result
import com.example.marveldatastone.model.CharacterModels.InfiniteNovel.InfiniteNovelData
import com.example.marveldatastone.widgets.BooksInfoCard
import kotlin.random.Random


@Composable
fun BooksInfoScreen(sharedViewModel: SharedViewModel) {


    val comicsList=sharedViewModel.comicsResult
    val hardCoverList=sharedViewModel.hardCoverResult
    val infiniteVovelList=sharedViewModel.infiniteNovelResult
    val tradePaperBookList=sharedViewModel.tradepaperbackResult
    val digestList=sharedViewModel.digestResult
    val graphicNovelList=sharedViewModel.graphicNovelResult
    //val infiniteNavellist=sharedViewModel.infiniteNovelResult

    var hclist by remember {
        mutableStateOf<Result?>(null)
    }

    LaunchedEffect(key1 = hardCoverList)
    {
            hclist=hardCoverList
    }


    var inlist by remember {
        mutableStateOf<com.example.marveldatastone.model.CharacterModels.InfiniteNovel.Result?>(null)
    }

    LaunchedEffect(key1 = infiniteVovelList)
    {
            inlist=infiniteVovelList
    }

    var tpblist by remember {
        mutableStateOf<com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.Result?>(null)
    }

    LaunchedEffect(key1 = tradePaperBookList )
    {
            tpblist=tradePaperBookList
    }

    var dlist by remember {
        mutableStateOf<com.example.marveldatastone.model.CharacterModels.Digest.Result?>(null)
    }

    LaunchedEffect(key1 = digestList )
    {
            dlist=digestList
    }

    var gnlist by remember {
        mutableStateOf<com.example.marveldatastone.model.CharacterModels.GraphicNovel.Result?>(
            null
        )
    }

    LaunchedEffect(key1 = graphicNovelList )
    {
            gnlist=graphicNovelList
    }

    var clist by remember {
        mutableStateOf<com.example.marveldatastone.model.CharacterModels.ComicsModels.Result?>(
            null
        )
    }

    LaunchedEffect(key1 = comicsList )
    {
        clist=comicsList
    }

//    LaunchedEffect(key1 = infiniteNavellist)
//    {
//        if(infiniteNavellist!=null)
//        {
//            title=infiniteNavellist.title
//        }
//    }

    if(clist!=null)
        Text(text = "Title : ${clist!!.title}")
    else if(hclist!=null)
    {
        //Text(text = "Title : ${hclist!!.title}"
        val url = "${hclist!!.thumbnail.path}.${hclist!!.thumbnail.extension}"
        val painter = rememberAsyncImagePainter(model = url)
        var title=if(hclist!!.title.isNullOrEmpty())
        {
            "Marvel Comics"
        }
        else
            hclist!!.title

        var pageCount=if(hclist!!.pageCount!= 0)
            hclist!!.pageCount
        else
            Random.nextInt(16,140)

        var format=if(hclist!!.format.isNullOrEmpty())
            "Comic"
        else
            hclist!!.format

        //price
        var priceD:Double=if(hclist!!.prices.isNullOrEmpty())
        {
            0.0
        }
        else
        {
            hclist!!.prices[0].price
        }
        var price:String
        var actualPrice:String
        if(priceD==0.0)
        {
            actualPrice="$"+String.format("%.2f",Random.nextDouble(5.0,34.0))
            price="Free"
        }
        else
        {
            actualPrice="$"+ String.format("%.2f",Random.nextDouble(priceD,priceD+45.0))
            price= "$$priceD"
        }

        var description=if(hclist!!.description.isNullOrEmpty())
        {
            "The latest entry in Marvel's $title OF series is no small matter! It's filled with full of Surprises. Visit the Marvel Website to Read More"
        }
        else
            hclist!!.description

        BooksInfoCard(painter = painter, title = title, pageCount = ""+pageCount,formate=format, price = price,actualPrice=actualPrice, description = description)
    }
    else if(inlist!=null)
        Text(text = "Title : ${inlist!!.title}")
    else if(tpblist!=null)
        Text(text = "Title : ${tpblist!!.title}")
    else if(dlist!=null)
        Text(text = "Title : ${dlist!!.title}")
    else if(gnlist!=null)
        Text(text = "Title : ${gnlist!!.title}")
    else{
        Surface(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    }

}