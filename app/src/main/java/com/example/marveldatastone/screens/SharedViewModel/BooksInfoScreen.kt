package com.example.marveldatastone.screens.SharedViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.rememberAsyncImagePainter
import com.example.marveldatastone.model.CharacterModels.HardCover.Image
import com.example.marveldatastone.model.CharacterModels.HardCover.Item
import com.example.marveldatastone.model.CharacterModels.HardCover.ItemX
import com.example.marveldatastone.model.CharacterModels.HardCover.Result
import com.example.marveldatastone.model.CharacterModels.InfiniteNovel.InfiniteNovelData
import com.example.marveldatastone.widgets.BooksInfoCard
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random


@RequiresApi(Build.VERSION_CODES.O)
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
            "The latest entry in Marvel's $title OF series is no small matter! It's filled with full of Surprises. Click the Buy Now Button to Read More"
        }
        else
            hclist!!.description

        var date:String
        var givendate=if(hclist!!.dates.isNullOrEmpty())
        {
            "${Random.nextInt(1996,2021)}-${Random.nextInt(10,12)}-${Random.nextInt(10,28)}"
        }
        else
        {
            hclist!!.dates[1].date
        }

        date=givendate.split("T")[0]
        var fdate = LocalDate.parse(date)
        var formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
        var formattedDate = fdate.format(formatter)

        // language
        val language=if(hclist!!.textObjects.isNullOrEmpty())
        {
            "en-us"
        }
        else
        {
            hclist!!.textObjects[0].language
        }

        //Related Images
        val painters:List<Painter>
        var imagelist = mutableListOf<Painter>()
        if(hclist!!.images.isNullOrEmpty())
        {
            imagelist= emptyList<Painter>() as MutableList<Painter>
        }
        else
        {
            val l=hclist!!.images
            for(i: Image in l)
            {
                val u = "${i.path}.${i.extension}"
                val p = rememberAsyncImagePainter(model = u)
                imagelist.add(p)
            }
        }
        imagelist.removeAt(0)
        painters=imagelist


        //Creators
        var creators = mutableListOf<String>()
        if(hclist!!.creators.items.isNullOrEmpty())
        {
            creators.add("")
            creators.removeAt(0)
        }
        else
        {
            var cl=hclist!!.creators.items
            for(i in cl)
            {
                creators.add("${i.name}\nRole: ${i.role}")
            }
        }


        //Characters
        var characters = mutableListOf<String>()
        if(hclist!!.characters.items.isNullOrEmpty())
        {
            characters.add("")
            characters.removeAt(0)
        }
        else
        {
            var crl=hclist!!.characters.items
            for(i in crl)
            {
                characters.add(i.name)
            }
        }

        BooksInfoCard(painter = painter, title = title, pageCount = ""+pageCount,formate=format, price = price,actualPrice=actualPrice, description = description, date = formattedDate, language = language, painters = painters, creators = creators, characters = characters)
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
            Card() {
                CircularProgressIndicator()
            }
        }
    }

}