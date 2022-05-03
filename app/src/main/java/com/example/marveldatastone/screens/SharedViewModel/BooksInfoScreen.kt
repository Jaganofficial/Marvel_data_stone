package com.example.marveldatastone.screens.SharedViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
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
    {
        val url = "${clist!!.thumbnail.path}.${clist!!.thumbnail.extension}"
        val painter = rememberAsyncImagePainter(model = url)
        var title=if(clist!!.title.isNullOrEmpty())
        {
            "Marvel Comics"
        }
        else
            clist!!.title

        var pageCount=if(clist!!.pageCount!= 0)
            clist!!.pageCount
        else
            Random.nextInt(16,140)

        var format=if(clist!!.format.isNullOrEmpty())
            "Comic"
        else
            clist!!.format

        //price
        var priceD:Double=if(clist!!.prices.isNullOrEmpty())
        {
            0.0
        }
        else
        {
            clist!!.prices[0].price
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

        var description=if(clist!!.description.isNullOrEmpty())
        {
            "The latest entry in Marvel's $title OF series is no small matter! It's filled with full of Surprises. Click the Buy Now Button to Read More"
        }
        else
            clist!!.description

        var date:String
        var givendate=if(clist!!.dates.isNullOrEmpty())
        {
            "${Random.nextInt(1996,2021)}-${Random.nextInt(10,12)}-${Random.nextInt(10,28)}"
        }
        else
        {
            clist!!.dates[1].date
        }

        date=givendate.split("T")[0]
        var fdate = LocalDate.parse(date)
        var formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
        var formattedDate = fdate.format(formatter)

        // language
        val language=if(clist!!.textObjects.isNullOrEmpty())
        {
            "en-us"
        }
        else
        {
            clist!!.textObjects[0].language
        }

        //Related Images
        val painters:List<Painter>
        var imagelist = mutableListOf<Painter>()
        if(clist!!.images.isNullOrEmpty())
        {
            imagelist= emptyList<Painter>() as MutableList<Painter>
        }
        else
        {
            val l=clist!!.images
            for(i: com.example.marveldatastone.model.CharacterModels.ComicsModels.Image in l)
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
        if(clist!!.creators.items.isNullOrEmpty())
        {
            creators.add("")
            creators.removeAt(0)
        }
        else
        {
            var cl=clist!!.creators.items
            for(i in cl)
            {
                creators.add("${i.name}\nRole: ${i.role}")
            }
        }


        //Characters
        var characters = mutableListOf<String>()
        if(clist!!.characters.items.isNullOrEmpty())
        {
            characters.add("")
            characters.removeAt(0)
        }
        else
        {
            var crl=clist!!.characters.items
            for(i in crl)
            {
                characters.add(i.name)
            }
        }

        BooksInfoCard(painter = painter, title = title, pageCount = ""+pageCount,formate=format, price = price,actualPrice=actualPrice, description = description, date = formattedDate, language = language, painters = painters, creators = creators, characters = characters)
    }
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
    {
        val url = "${inlist!!.thumbnail.path}.${inlist!!.thumbnail.extension}"
        val painter = rememberAsyncImagePainter(model = url)
        var title=if(inlist!!.title.isNullOrEmpty())
        {
            "Marvel Comics"
        }
        else
            inlist!!.title

        var pageCount=if(inlist!!.pageCount!= 0)
            inlist!!.pageCount
        else
            Random.nextInt(16,140)

        var format=if(inlist!!.format.isNullOrEmpty())
            "Comic"
        else
            inlist!!.format

        //price
        var priceD:Double=if(inlist!!.prices.isNullOrEmpty())
        {
            0.0
        }
        else
        {
            inlist!!.prices[0].price
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

        var description=if(inlist!!.description.isNullOrEmpty())
        {
            "The latest entry in Marvel's $title OF series is no small matter! It's filled with full of Surprises. Click the Buy Now Button to Read More"
        }
        else
            inlist!!.description

        var date:String
        var givendate=if(inlist!!.dates.isNullOrEmpty())
        {
            "${Random.nextInt(1996,2021)}-${Random.nextInt(10,12)}-${Random.nextInt(10,28)}"
        }
        else
        {
            inlist!!.dates[1].date
        }

        date=givendate.split("T")[0]
        var fdate = LocalDate.parse(date)
        var formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
        var formattedDate = fdate.format(formatter)

        // language
        val language=if(inlist!!.textObjects.isNullOrEmpty())
        {
            "en-us"
        }
        else
        {
            inlist!!.textObjects[0].language
        }

        //Related Images
        val painters:List<Painter>
        var imagelist = mutableListOf<Painter>()
        if(inlist!!.images.isNullOrEmpty())
        {
            imagelist= emptyList<Painter>() as MutableList<Painter>
        }
        else
        {
            val l=inlist!!.images
            for(i: com.example.marveldatastone.model.CharacterModels.InfiniteNovel.Image in l)
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
        if(inlist!!.creators.items.isNullOrEmpty())
        {
            creators.add("")
            creators.removeAt(0)
        }
        else
        {
            var cl=inlist!!.creators.items
            for(i in cl)
            {
                creators.add("${i.name}\nRole: ${i.role}")
            }
        }


        //Characters
        var characters = mutableListOf<String>()
        if(inlist!!.characters.items.isNullOrEmpty())
        {
            characters.add("")
            characters.removeAt(0)
        }
        else
        {
            var crl=inlist!!.characters.items
            for(i in crl)
            {
                characters.add(i.name)
            }
        }

        BooksInfoCard(painter = painter, title = title, pageCount = ""+pageCount,formate=format, price = price,actualPrice=actualPrice, description = description, date = formattedDate, language = language, painters = painters, creators = creators, characters = characters)
    }
    else if(tpblist!=null)
    {
        val url = "${tpblist!!.thumbnail.path}.${tpblist!!.thumbnail.extension}"
        val painter = rememberAsyncImagePainter(model = url)
        var title=if(tpblist!!.title.isNullOrEmpty())
        {
            "Marvel Comics"
        }
        else
            tpblist!!.title

        var pageCount=if(tpblist!!.pageCount!= 0)
            tpblist!!.pageCount
        else
            Random.nextInt(16,140)

        var format=if(tpblist!!.format.isNullOrEmpty())
            "Comic"
        else
            tpblist!!.format

        //price
        var priceD:Double=if(tpblist!!.prices.isNullOrEmpty())
        {
            0.0
        }
        else
        {
            tpblist!!.prices[0].price
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

        var description=if(tpblist!!.description.isNullOrEmpty())
        {
            "The latest entry in Marvel's $title OF series is no small matter! It's filled with full of Surprises. Click the Buy Now Button to Read More"
        }
        else
            tpblist!!.description

        var date:String
        var givendate=if(tpblist!!.dates.isNullOrEmpty())
        {
            "${Random.nextInt(1996,2021)}-${Random.nextInt(10,12)}-${Random.nextInt(10,28)}"
        }
        else
        {
            tpblist!!.dates[1].date
        }

        date=givendate.split("T")[0]
        var fdate = LocalDate.parse(date)
        var formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
        var formattedDate = fdate.format(formatter)

        // language
        val language=if(tpblist!!.textObjects.isNullOrEmpty())
        {
            "en-us"
        }
        else
        {
            tpblist!!.textObjects[0].language
        }

        //Related Images
        val painters:List<Painter>
        var imagelist = mutableListOf<Painter>()
        if(tpblist!!.images.isNullOrEmpty())
        {
            imagelist= emptyList<Painter>() as MutableList<Painter>
        }
        else
        {
            val l=tpblist!!.images
            for(i: com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.Image in l)
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
        if(tpblist!!.creators.items.isNullOrEmpty())
        {
            creators.add("")
            creators.removeAt(0)
        }
        else
        {
            var cl=tpblist!!.creators.items
            for(i in cl)
            {
                creators.add("${i.name}\nRole: ${i.role}")
            }
        }


        //Characters
        var characters = mutableListOf<String>()
        if(tpblist!!.characters.items.isNullOrEmpty())
        {
            characters.add("")
            characters.removeAt(0)
        }
        else
        {
            var crl=tpblist!!.characters.items
            for(i in crl)
            {
                characters.add(i.name)
            }
        }

        BooksInfoCard(painter = painter, title = title, pageCount = ""+pageCount,formate=format, price = price,actualPrice=actualPrice, description = description, date = formattedDate, language = language, painters = painters, creators = creators, characters = characters)
    }
    else if(dlist!=null)
    {
        val url = "${dlist!!.thumbnail.path}.${dlist!!.thumbnail.extension}"
        val painter = rememberAsyncImagePainter(model = url)
        var title=if(dlist!!.title.isNullOrEmpty())
        {
            "Marvel Comics"
        }
        else
            dlist!!.title

        var pageCount=if(dlist!!.pageCount!= 0)
            dlist!!.pageCount
        else
            Random.nextInt(16,140)

        var format=if(dlist!!.format.isNullOrEmpty())
            "Comic"
        else
            dlist!!.format

        //price
        var priceD:Double=if(dlist!!.prices.isNullOrEmpty())
        {
            0.0
        }
        else
        {
            dlist!!.prices[0].price
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

        var description=if(dlist!!.description.isNullOrEmpty())
        {
            "The latest entry in Marvel's $title OF series is no small matter! It's filled with full of Surprises. Click the Buy Now Button to Read More"
        }
        else
            dlist!!.description

        var date:String
        var givendate=if(dlist!!.dates.isNullOrEmpty())
        {
            "${Random.nextInt(1996,2021)}-${Random.nextInt(10,12)}-${Random.nextInt(10,28)}"
        }
        else
        {
            dlist!!.dates[1].date
        }

        date=givendate.split("T")[0]
        var fdate = LocalDate.parse(date)
        var formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
        var formattedDate = fdate.format(formatter)

        // language
        val language=if(dlist!!.textObjects.isNullOrEmpty())
        {
            "en-us"
        }
        else
        {
            dlist!!.textObjects[0].language
        }

        //Related Images
        val painters:List<Painter>
        var imagelist = mutableListOf<Painter>()
        if(dlist!!.images.isNullOrEmpty())
        {
            imagelist= emptyList<Painter>() as MutableList<Painter>
        }
        else
        {
            val l=dlist!!.images
            for(i: com.example.marveldatastone.model.CharacterModels.Digest.Image in l)
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
        if(dlist!!.creators.items.isNullOrEmpty())
        {
            creators.add("")
            creators.removeAt(0)
        }
        else
        {
            var cl=dlist!!.creators.items
            for(i in cl)
            {
                creators.add("${i.name}\nRole: ${i.role}")
            }
        }


        //Characters
        var characters = mutableListOf<String>()
        if(dlist!!.characters.items.isNullOrEmpty())
        {
            characters.add("")
            characters.removeAt(0)
        }
        else
        {
            var crl=dlist!!.characters.items
            for(i in crl)
            {
                characters.add(i.name)
            }
        }

        BooksInfoCard(painter = painter, title = title, pageCount = ""+pageCount,formate=format, price = price,actualPrice=actualPrice, description = description, date = formattedDate, language = language, painters = painters, creators = creators, characters = characters)
    }
    else if(gnlist!=null)
    {
        val url = "${gnlist!!.thumbnail.path}.${gnlist!!.thumbnail.extension}"
        val painter = rememberAsyncImagePainter(model = url)
        var title=if(gnlist!!.title.isNullOrEmpty())
        {
            "Marvel Comics"
        }
        else
            gnlist!!.title

        var pageCount=if(gnlist!!.pageCount!= 0)
            gnlist!!.pageCount
        else
            Random.nextInt(16,140)

        var format=if(gnlist!!.format.isNullOrEmpty())
            "Comic"
        else
            gnlist!!.format

        //price
        var priceD:Double=if(gnlist!!.prices.isNullOrEmpty())
        {
            0.0
        }
        else
        {
            gnlist!!.prices[0].price
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

        var description=if(gnlist!!.description.isNullOrEmpty())
        {
            "The latest entry in Marvel's $title OF series is no small matter! It's filled with full of Surprises. Click the Buy Now Button to Read More"
        }
        else
            gnlist!!.description

        var date:String
        var givendate=if(gnlist!!.dates.isNullOrEmpty())
        {
            "${Random.nextInt(1996,2021)}-${Random.nextInt(10,12)}-${Random.nextInt(10,28)}"
        }
        else
        {
            gnlist!!.dates[1].date
        }

        date=givendate.split("T")[0]
        var fdate = LocalDate.parse(date)
        var formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
        var formattedDate = fdate.format(formatter)

        // language
        val language=if(gnlist!!.textObjects.isNullOrEmpty())
        {
            "en-us"
        }
        else
        {
            gnlist!!.textObjects[0].language
        }

        //Related Images
        val painters:List<Painter>
        var imagelist = mutableListOf<Painter>()
        if(gnlist!!.images.isNullOrEmpty())
        {
            imagelist= emptyList<Painter>() as MutableList<Painter>
        }
        else
        {
            val l=gnlist!!.images
            for(i: com.example.marveldatastone.model.CharacterModels.GraphicNovel.Image in l)
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
        if(gnlist!!.creators.items.isNullOrEmpty())
        {
            creators.add("")
            creators.removeAt(0)
        }
        else
        {
            var cl=gnlist!!.creators.items
            for(i in cl)
            {
                creators.add("${i.name}\nRole: ${i.role}")
            }
        }


        //Characters
        var characters = mutableListOf<String>()
        if(gnlist!!.characters.items.isNullOrEmpty())
        {
            characters.add("")
            characters.removeAt(0)
        }
        else
        {
            var crl=gnlist!!.characters.items
            for(i in crl)
            {
                characters.add(i.name)
            }
        }
        BooksInfoCard(painter = painter, title = title, pageCount = ""+pageCount,formate=format, price = price,actualPrice=actualPrice, description = description, date = formattedDate, language = language, painters = painters, creators = creators, characters = characters)
    }
    else{
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.size(65.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }

}