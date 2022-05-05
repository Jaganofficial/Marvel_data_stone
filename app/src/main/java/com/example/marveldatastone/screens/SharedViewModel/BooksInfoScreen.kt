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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.marveldatastone.model.CharacterModels.HardCover.Image
import com.example.marveldatastone.model.CharacterModels.HardCover.Item
import com.example.marveldatastone.model.CharacterModels.HardCover.ItemX
import com.example.marveldatastone.model.CharacterModels.HardCover.Result
import com.example.marveldatastone.model.CharacterModels.InfiniteNovel.InfiniteNovelData
import com.example.marveldatastone.repository.DataStore.DataStoreRepository
import com.example.marveldatastone.repository.DataStore.DataStoreRepositoryViewModel
import com.example.marveldatastone.widgets.BooksInfoCard
import java.lang.IllegalStateException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BooksInfoScreen(sharedViewModel: SharedViewModel,navController: NavController,dataStoreRepositoryViewModel: DataStoreRepositoryViewModel= viewModel(factory = DataStoreViewModelFactory(
    DataStoreRepository(LocalContext.current)
))) {


    val comicsList=sharedViewModel.comicsResult
    val hardCoverList=sharedViewModel.hardCoverResult
    val infiniteVovelList=sharedViewModel.infiniteNovelResult
    val tradePaperBookList=sharedViewModel.tradepaperbackResult
    val digestList=sharedViewModel.digestResult
    val graphicNovelList=sharedViewModel.graphicNovelResult

    var hclist by remember {
        mutableStateOf<Result?>(null)
    }


    //DataStore
    val context= LocalContext.current
    val dataStoreRepository:DataStoreRepository= DataStoreRepository.getDataStoreInstence(context = context)
    val getId=dataStoreRepositoryViewModel.favorites.observeAsState().value
    val scope= rememberCoroutineScope()



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

    when {
        clist!=null -> {
            val url = "${clist!!.thumbnail.path}.${clist!!.thumbnail.extension}"
            val painter = rememberAsyncImagePainter(model = url)
            val title=if(clist!!.title.isEmpty()) {
                "Marvel Comics"
            } else
                clist!!.title

            val pageCount=if(clist!!.pageCount!= 0)
                clist!!.pageCount
            else
                Random.nextInt(16,140)

            val format=if(clist!!.format.isEmpty())
                "Comic"
            else
                clist!!.format

            //price
            val priceD:Double=if(clist!!.prices.isNullOrEmpty()) {
                0.0
            } else {
                clist!!.prices[0].price
            }
            val price:String
            val actualPrice:String
            if(priceD==0.0) {
                actualPrice="$"+String.format("%.2f",Random.nextDouble(5.0,34.0))
                price="Free"
            } else {
                actualPrice="$"+ String.format("%.2f",Random.nextDouble(priceD,priceD+45.0))
                price= "$$priceD"
            }

            val description=if((clist!!.description.isNullOrEmpty())) {
                "The latest entry in Marvel's $title OF series is no small matter! It's filled with full of Surprises. Click the Buy Now Button to Read More"
            } else
                clist!!.description

            val date:String
            val givendate=if(clist!!.dates.isNullOrEmpty()) {
                "${Random.nextInt(1996,2021)}-${Random.nextInt(10,12)}-${Random.nextInt(10,28)}"
            } else {
                clist!!.dates[1].date
            }

            date=givendate.split("T")[0]
            val fdate = LocalDate.parse(date)
            val formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
            val formattedDate = fdate.format(formatter)

            // language
            val language=if(clist!!.textObjects.isNullOrEmpty()) {
                "en-us"
            } else {
                clist!!.textObjects[0].language
            }

            //Related Images
            val painters:List<Painter>
            val imagelist = mutableListOf<Painter>()
            if(clist!!.images.isNullOrEmpty()) {
                //imagelist= emptyList<Painter>() as MutableList<Painter>
            } else {
                val l=clist!!.images
                for(i: com.example.marveldatastone.model.CharacterModels.ComicsModels.Image in l) {
                    val u = "${i.path}.${i.extension}"
                    val p = rememberAsyncImagePainter(model = u)
                    imagelist.add(p)
                }
                imagelist.removeAt(0)
            }

            painters=imagelist


            //Creators
            val creators = mutableListOf<String>()
            if(clist!!.creators.items.isNullOrEmpty()) {
                creators.add("")
                creators.removeAt(0)
            } else {
                val cl=clist!!.creators.items
                for(i in cl) {
                    creators.add("${i.name}\nRole: ${i.role}")
                }
            }


            //Characters
            val characters = mutableListOf<String>()
            if(clist!!.characters.items.isNullOrEmpty()) {
                characters.add("")
                characters.removeAt(0)
            } else {
                val crl=clist!!.characters.items
                for(i in crl) {
                    characters.add(i.name)
                }
            }

            val dataurl:String
            if(clist!!.urls.isNullOrEmpty()) {
                dataurl="https://www.marvel.com/comics"
            } else {
                dataurl=clist!!.urls[0].url
            }


            BooksInfoCard(painter = painter, title = title, pageCount = ""+pageCount,formate=format, price = price,actualPrice=actualPrice, description = description, date = formattedDate, language = language, painters = painters, creators = creators, characters = characters, dataUrl = dataurl, navController = navController, dataStoreRepository = dataStoreRepository,ID=getId, scope = scope, bookId = "${clist!!.id}")
        }
        hclist!=null -> {
            //Text(text = "Title : ${hclist!!.title}"
            val url = "${hclist!!.thumbnail.path}.${hclist!!.thumbnail.extension}"
            val painter = rememberAsyncImagePainter(model = url)
            val title=if(hclist!!.title.isEmpty()) {
                "Marvel Comics"
            } else
                hclist!!.title

            val pageCount=if(hclist!!.pageCount!= 0)
                hclist!!.pageCount
            else
                Random.nextInt(16,140)

            val format=if(hclist!!.format.isEmpty())
                "Comic"
            else
                hclist!!.format

            //price
            val priceD:Double=if(hclist!!.prices.isNullOrEmpty()) {
                0.0
            } else {
                hclist!!.prices[0].price
            }
            val price:String
            val actualPrice:String
            if(priceD==0.0) {
                actualPrice="$"+String.format("%.2f",Random.nextDouble(5.0,34.0))
                price="Free"
            } else {
                actualPrice="$"+ String.format("%.2f",Random.nextDouble(priceD,priceD+45.0))
                price= "$$priceD"
            }

            val description=if(hclist!!.description.isNullOrEmpty()) {
                "The latest entry in Marvel's $title OF series is no small matter! It's filled with full of Surprises. Click the Buy Now Button to Read More"
            } else
                hclist!!.description

            val date:String
            val givendate=if(hclist!!.dates.isNullOrEmpty()) {
                "${Random.nextInt(1996,2021)}-${Random.nextInt(10,12)}-${Random.nextInt(10,28)}"
            } else {
                hclist!!.dates[1].date
            }

            date=givendate.split("T")[0]
            val fdate = LocalDate.parse(date)
            val formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
            val formattedDate = fdate.format(formatter)

            // language
            val language=if(hclist!!.textObjects.isNullOrEmpty()) {
                "en-us"
            } else {
                hclist!!.textObjects[0].language
            }

            //Related Images
            val painters:List<Painter>
            val imagelist = mutableListOf<Painter>()
            if(hclist!!.images.isNullOrEmpty()) {
                //imagelist= emptyList<Painter>() as MutableList<Painter>
            } else {
                val l=hclist!!.images
                for(i: Image in l) {
                    val u = "${i.path}.${i.extension}"
                    val p = rememberAsyncImagePainter(model = u)
                    imagelist.add(p)
                }
                imagelist.removeAt(0)
            }

            painters=imagelist


            //Creators
            val creators = mutableListOf<String>()
            if(hclist!!.creators.items.isNullOrEmpty()) {
                creators.add("")
                creators.removeAt(0)
            } else {
                val cl=hclist!!.creators.items
                for(i in cl) {
                    creators.add("${i.name}\nRole: ${i.role}")
                }
            }


            //Characters
            val characters = mutableListOf<String>()
            if(hclist!!.characters.items.isNullOrEmpty()) {
                characters.add("")
                characters.removeAt(0)
            } else {
                val crl=hclist!!.characters.items
                for(i in crl) {
                    characters.add(i.name)
                }
            }

            val dataurl:String
            if(hclist!!.urls.isNullOrEmpty()) {
                dataurl="https://www.marvel.com/comics"
            } else {
                dataurl=hclist!!.urls[0].url
            }

            BooksInfoCard(painter = painter, title = title, pageCount = ""+pageCount,formate=format, price = price,actualPrice=actualPrice, description = description, date = formattedDate, language = language, painters = painters, creators = creators, characters = characters, dataUrl = dataurl, navController = navController, dataStoreRepository = dataStoreRepository,ID=getId, scope = scope, bookId = "${hclist!!.id}")
        }
        inlist!=null -> {
            val url = "${inlist!!.thumbnail.path}.${inlist!!.thumbnail.extension}"
            val painter = rememberAsyncImagePainter(model = url)
            val title=if(inlist!!.title.isEmpty()) {
                "Marvel Comics"
            } else
                inlist!!.title

            val pageCount=if(inlist!!.pageCount!= 0)
                inlist!!.pageCount
            else
                Random.nextInt(16,140)

            val format=if(inlist!!.format.isEmpty())
                "Comic"
            else
                inlist!!.format

            //price
            val priceD:Double=if(inlist!!.prices.isNullOrEmpty()) {
                0.0
            } else {
                inlist!!.prices[0].price
            }
            val price:String
            val actualPrice:String
            if(priceD==0.0) {
                actualPrice="$"+String.format("%.2f",Random.nextDouble(5.0,34.0))
                price="Free"
            } else {
                actualPrice="$"+ String.format("%.2f",Random.nextDouble(priceD,priceD+45.0))
                price= "$$priceD"
            }

            val description=if(inlist!!.description.isNullOrEmpty()) {
                "The latest entry in Marvel's $title OF series is no small matter! It's filled with full of Surprises. Click the Buy Now Button to Read More"
            } else
                inlist!!.description

            val date:String
            val givendate=if(inlist!!.dates.isNullOrEmpty()) {
                "${Random.nextInt(1996,2021)}-${Random.nextInt(10,12)}-${Random.nextInt(10,28)}"
            } else {
                inlist!!.dates[1].date
            }

            date=givendate.split("T")[0]
            val fdate = LocalDate.parse(date)
            val formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
            val formattedDate = fdate.format(formatter)

            // language
            val language=if(inlist!!.textObjects.isNullOrEmpty()) {
                "en-us"
            } else {
                inlist!!.textObjects[0].language
            }

            //Related Images
            val painters:List<Painter>
            val imagelist = mutableListOf<Painter>()
            if(inlist!!.images.isNullOrEmpty()) {
                //imagelist= emptyList<Painter>() as MutableList<Painter>
            } else {
                val l=inlist!!.images
                for(i: com.example.marveldatastone.model.CharacterModels.InfiniteNovel.Image in l) {
                    val u = "${i.path}.${i.extension}"
                    val p = rememberAsyncImagePainter(model = u)
                    imagelist.add(p)
                }
                imagelist.removeAt(0)
            }

            painters=imagelist


            //Creators
            val creators = mutableListOf<String>()
            if(inlist!!.creators.items.isNullOrEmpty()) {
                creators.add("")
                creators.removeAt(0)
            } else {
                val cl=inlist!!.creators.items
                for(i in cl) {
                    creators.add("${i.name}\nRole: ${i.role}")
                }
            }


            //Characters
            val characters = mutableListOf<String>()
            if(inlist!!.characters.items.isNullOrEmpty()) {
                characters.add("")
                characters.removeAt(0)
            } else {
                val crl=inlist!!.characters.items
                for(i in crl) {
                    characters.add(i.name)
                }
            }

            val dataurl:String
            if(inlist!!.urls.isNullOrEmpty()) {
                dataurl="https://www.marvel.com/comics"
            } else {
                dataurl=inlist!!.urls[0].url
            }


            BooksInfoCard(painter = painter, title = title, pageCount = ""+pageCount,formate=format, price = price,actualPrice=actualPrice, description = description, date = formattedDate, language = language, painters = painters, creators = creators, characters = characters, dataUrl = dataurl, navController = navController, dataStoreRepository = dataStoreRepository,ID=getId, scope = scope, bookId = "${inlist!!.id}")
        }
        tpblist!=null -> {
            val url = "${tpblist!!.thumbnail.path}.${tpblist!!.thumbnail.extension}"
            val painter = rememberAsyncImagePainter(model = url)
            val title=if(tpblist!!.title.isEmpty()) {
                "Marvel Comics"
            } else
                tpblist!!.title

            val pageCount=if(tpblist!!.pageCount!= 0)
                tpblist!!.pageCount
            else
                Random.nextInt(16,140)

            val format=if(tpblist!!.format.isEmpty())
                "Comic"
            else
                tpblist!!.format

            //price
            val priceD:Double=if(tpblist!!.prices.isNullOrEmpty()) {
                0.0
            } else {
                tpblist!!.prices[0].price
            }
            val price:String
            val actualPrice:String
            if(priceD==0.0) {
                actualPrice="$"+String.format("%.2f",Random.nextDouble(5.0,34.0))
                price="Free"
            } else {
                actualPrice="$"+ String.format("%.2f",Random.nextDouble(priceD,priceD+45.0))
                price= "$$priceD"
            }

            val description=if(tpblist!!.description.isNullOrEmpty()) {
                "The latest entry in Marvel's $title OF series is no small matter! It's filled with full of Surprises. Click the Buy Now Button to Read More"
            } else
                tpblist!!.description

            val date:String
            val givendate=if(tpblist!!.dates.isNullOrEmpty()) {
                "${Random.nextInt(1996,2021)}-${Random.nextInt(10,12)}-${Random.nextInt(10,28)}"
            } else {
                tpblist!!.dates[1].date
            }

            date=givendate.split("T")[0]
            val fdate = LocalDate.parse(date)
            val formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
            val formattedDate = fdate.format(formatter)

            // language
            val language=if(tpblist!!.textObjects.isNullOrEmpty()) {
                "en-us"
            } else {
                tpblist!!.textObjects[0].language
            }

            //Related Images
            val painters:List<Painter>
            val imagelist = mutableListOf<Painter>()
            if(tpblist!!.images.isNullOrEmpty()) {
                //imagelist= emptyList<Painter>() as MutableList<Painter>
            } else {
                val l=tpblist!!.images
                for(i: com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.Image in l) {
                    val u = "${i.path}.${i.extension}"
                    val p = rememberAsyncImagePainter(model = u)
                    imagelist.add(p)
                }
                imagelist.removeAt(0)
            }
            painters=imagelist


            //Creators
            val creators = mutableListOf<String>()
            if(tpblist!!.creators.items.isNullOrEmpty()) {
                creators.add("")
                creators.removeAt(0)
            } else {
                val cl=tpblist!!.creators.items
                for(i in cl) {
                    creators.add("${i.name}\nRole: ${i.role}")
                }
            }


            //Characters
            val characters = mutableListOf<String>()
            if(tpblist!!.characters.items.isNullOrEmpty()) {
                characters.add("")
                characters.removeAt(0)
            } else {
                val crl=tpblist!!.characters.items
                for(i in crl) {
                    characters.add(i.name)
                }
            }

            val dataurl:String
            if(tpblist!!.urls.isNullOrEmpty()) {
                dataurl="https://www.marvel.com/comics"
            } else {
                dataurl=tpblist!!.urls[0].url
            }

            BooksInfoCard(painter = painter, title = title, pageCount = ""+pageCount,formate=format, price = price,actualPrice=actualPrice, description = description, date = formattedDate, language = language, painters = painters, creators = creators, characters = characters, dataUrl = dataurl, navController = navController, dataStoreRepository = dataStoreRepository,ID=getId, scope = scope, bookId = "${tpblist!!.id}")
        }
        dlist!=null -> {
            val url = "${dlist!!.thumbnail.path}.${dlist!!.thumbnail.extension}"
            val painter = rememberAsyncImagePainter(model = url)
            val title=if(dlist!!.title.isEmpty()) {
                "Marvel Comics"
            } else
                dlist!!.title

            val pageCount=if(dlist!!.pageCount!= 0)
                dlist!!.pageCount
            else
                Random.nextInt(16,140)

            val format=if(dlist!!.format.isEmpty())
                "Comic"
            else
                dlist!!.format

            //price
            val priceD:Double=if(dlist!!.prices.isNullOrEmpty()) {
                0.0
            } else {
                dlist!!.prices[0].price
            }
            val price:String
            val actualPrice:String
            if(priceD==0.0) {
                actualPrice="$"+String.format("%.2f",Random.nextDouble(5.0,34.0))
                price="Free"
            } else {
                actualPrice="$"+ String.format("%.2f",Random.nextDouble(priceD,priceD+45.0))
                price= "$$priceD"
            }

            val description=if(dlist!!.description.isNullOrEmpty()) {
                "The latest entry in Marvel's $title OF series is no small matter! It's filled with full of Surprises. Click the Buy Now Button to Read More"
            } else
                dlist!!.description

            val date:String
            val givendate=if(dlist!!.dates.isNullOrEmpty()) {
                "${Random.nextInt(1996,2021)}-${Random.nextInt(10,12)}-${Random.nextInt(10,28)}"
            } else {
                dlist!!.dates[1].date
            }

            date=givendate.split("T")[0]
            val fdate = LocalDate.parse(date)
            val formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
            val formattedDate = fdate.format(formatter)

            // language
            val language=if(dlist!!.textObjects.isNullOrEmpty()) {
                "en-us"
            } else {
                dlist!!.textObjects[0].language
            }

            //Related Images
            val painters:List<Painter>
            var imagelist = mutableListOf<Painter>()
            if(dlist!!.images.isNullOrEmpty()) {
                //imagelist=MutableList<Painter>
            } else {
                val l=dlist!!.images
                for(i: com.example.marveldatastone.model.CharacterModels.Digest.Image in l) {
                    val u = "${i.path}.${i.extension}"
                    val p = rememberAsyncImagePainter(model = u)
                    imagelist.add(p)
                }
                imagelist.removeAt(0)
            }

            painters=imagelist


            //Creators
            val creators = mutableListOf<String>()
            if(dlist!!.creators.items.isNullOrEmpty()) {
                creators.add("")
                creators.removeAt(0)
            } else {
                val cl=dlist!!.creators.items
                for(i in cl) {
                    creators.add("${i.name}\nRole: ${i.role}")
                }
            }


            //Characters
            val characters = mutableListOf<String>()
            if(dlist!!.characters.items.isNullOrEmpty()) {
                characters.add("")
                characters.removeAt(0)
            } else {
                val crl=dlist!!.characters.items
                for(i in crl) {
                    characters.add(i.name)
                }
            }
//url
            val dataurl:String
            if(dlist!!.urls.isNullOrEmpty()) {
                dataurl="https://www.marvel.com/comics"
            } else {
                dataurl=dlist!!.urls[0].url
            }



            BooksInfoCard(painter = painter, title = title, pageCount = ""+pageCount,formate=format, price = price,actualPrice=actualPrice, description = description, date = formattedDate, language = language, painters = painters, creators = creators, characters = characters, dataUrl = dataurl, navController = navController, dataStoreRepository = dataStoreRepository,ID=getId, scope = scope, bookId = "${dlist!!.id}")
        }
        gnlist!=null -> {
            val url = "${gnlist!!.thumbnail.path}.${gnlist!!.thumbnail.extension}"
            val painter = rememberAsyncImagePainter(model = url)
            val title=if(gnlist!!.title.isEmpty()) {
                "Marvel Comics"
            } else
                gnlist!!.title

            val pageCount=if(gnlist!!.pageCount!= 0)
                gnlist!!.pageCount
            else
                Random.nextInt(16,140)

            val format=if(gnlist!!.format.isEmpty())
                "Comic"
            else
                gnlist!!.format

            //price
            val priceD:Double=if(gnlist!!.prices.isNullOrEmpty()) {
                0.0
            } else {
                gnlist!!.prices[0].price
            }
            val price:String
            val actualPrice:String
            if(priceD==0.0) {
                actualPrice="$"+String.format("%.2f",Random.nextDouble(5.0,34.0))
                price="Free"
            } else {
                actualPrice="$"+ String.format("%.2f",Random.nextDouble(priceD,priceD+45.0))
                price= "$$priceD"
            }

            val description=if(gnlist!!.description.isNullOrEmpty()) {
                "The latest entry in Marvel's $title OF series is no small matter! It's filled with full of Surprises. Click the Buy Now Button to Read More"
            } else
                gnlist!!.description

            val date:String
            val givendate=if(gnlist!!.dates.isNullOrEmpty()) {
                "${Random.nextInt(1996,2021)}-${Random.nextInt(10,12)}-${Random.nextInt(10,28)}"
            } else {
                gnlist!!.dates[1].date
            }

            date=givendate.split("T")[0]
            val fdate = LocalDate.parse(date)
            val formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
            val formattedDate = fdate.format(formatter)

            // language
            val language=if(gnlist!!.textObjects.isNullOrEmpty()) {
                "en-us"
            } else {
                gnlist!!.textObjects[0].language
            }

            //Related Images
            val painters:List<Painter>
            val imagelist = mutableListOf<Painter>()
            if(gnlist!!.images.isNullOrEmpty()) {
                //imagelist= emptyList<Painter>() as MutableList<Painter>
            } else {
                val l=gnlist!!.images
                for(i: com.example.marveldatastone.model.CharacterModels.GraphicNovel.Image in l) {
                    val u = "${i.path}.${i.extension}"
                    val p = rememberAsyncImagePainter(model = u)
                    imagelist.add(p)
                }
                imagelist.removeAt(0)
            }

            painters=imagelist


            //Creators
            val creators = mutableListOf<String>()
            if(gnlist!!.creators.items.isNullOrEmpty()) {
                creators.add("")
                creators.removeAt(0)
            } else {
                val cl=gnlist!!.creators.items
                for(i in cl) {
                    creators.add("${i.name}\nRole: ${i.role}")
                }
            }


            //Characters
            val characters = mutableListOf<String>()
            if(gnlist!!.characters.items.isNullOrEmpty()) {
                characters.add("")
                characters.removeAt(0)
            } else {
                val crl=gnlist!!.characters.items
                for(i in crl) {
                    characters.add(i.name)
                }
            }


            //url
            val dataurl:String
            if(gnlist!!.urls.isNullOrEmpty()) {
                dataurl="https://www.marvel.com/comics"
            } else {
                dataurl=gnlist!!.urls[0].url
            }
            BooksInfoCard(painter = painter, title = title, pageCount = ""+pageCount,formate=format, price = price,actualPrice=actualPrice, description = description, date = formattedDate, language = language, painters = painters, creators = creators, characters = characters, dataUrl = dataurl, navController = navController, dataStoreRepository = dataStoreRepository,ID=getId, scope = scope, bookId = "${gnlist!!.id}")
        }
        else -> {
            Surface(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.size(65.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }

}

class DataStoreViewModelFactory (private val dataStoreRepository: DataStoreRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DataStoreRepositoryViewModel::class.java))
        {
           return DataStoreRepositoryViewModel(dataStoreRepository) as T
        }
        throw IllegalStateException()
    }
}