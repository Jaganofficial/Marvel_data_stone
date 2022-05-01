package com.example.marveldatastone.screens.BooksScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.marveldatastone.R
import com.example.marveldatastone.data.DataOrException
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
import com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.TradePaperBookData
import com.example.marveldatastone.screens.Main.MainViewModel
import com.example.marveldatastone.widgets.BookCard
import com.example.marveldatastone.widgets.ImageCard
import okhttp3.internal.wait
import kotlin.random.Random


@SuppressLint("StateFlowValueCalledInComposition", "ProduceStateDoesNotAssignValue")
@Composable
fun BooksScreen (navController: NavController, booksViewModel: BooksViewModel){
    Surface(modifier = Modifier.fillMaxSize()) {
        var scrollableState= rememberScrollState()
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollableState),verticalArrangement = Arrangement.SpaceEvenly) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(350.dp), shape = RoundedCornerShape(bottomEnd = 350.dp, bottomStart = 35.dp)) {
                Box()
                {

                        Image(
                            painter = painterResource(id = R.drawable.marvel_comics),
                            contentDescription = "Marvel Comics",
                            contentScale = ContentScale.Crop
                        )
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top)
                    {
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(horizontal = 24.dp, vertical = 5.dp), shape = RoundedCornerShape(25.dp), backgroundColor = Color.White) {
                            Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "Search all 27,000 Marvel comics", textAlign = TextAlign.Center, style = TextStyle(color = Color.Gray, fontSize = 14.sp))
                                Image(imageVector = Icons.Default.Search, contentDescription ="Books Search Image", modifier = Modifier.size(40.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(25.dp))
                        Text(text = buildAnnotatedString {
                            append("Search\n")
                            withStyle(
                                style = SpanStyle(
                                    Color(255, 102, 102),
                                    fontSize = 37.sp,
                                )
                            )
                            {
                                append("Marvel\n")
                            }
                            append("Books...")
                        }, color = Color.White, fontSize=35.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.ExtraBold, modifier = Modifier.offset(35.dp))
                    }
                }
            }

            Column(modifier = Modifier) {
                Spacer(modifier = Modifier.height(18.dp))



                Spacer(modifier = Modifier.height(18.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Infinite Novel",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onSecondary
                        )
                    )
                    Text(
                        text = "See All",
                        style = TextStyle(
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(23, 212, 252)
                        )
                    )
                }
                if(booksViewModel.infiniteNavelList.collectAsState().value.isEmpty()) {

                    val infiniteNovelData =
                        produceState<DataOrException<TradePaperBookData, Boolean, Exception>>(
                            initialValue = DataOrException(loading = true)
                        ) {
                            value = booksViewModel.getInfiniteComic()
                        }.value

                    if (infiniteNovelData.loading == true) {
                        val painter = rememberAsyncImagePainter(model = R.color.white)
                        LazyRow()
                        {
                            items(100)
                            {
                                BookCard(
                                    Color(
                                        Random.nextInt(170, 255),
                                        Random.nextInt(170, 255),
                                        Random.nextInt(175, 255)
                                    ), painter = painter, true
                                )
                            }
                        }
                    } else {
                        Log.d("BOOK1", "${booksViewModel.graphicNovelList.collectAsState().value}")
                        val list = infiniteNovelData.data!!.data.results
                        LazyRow()
                        {
                            items(list)
                            {

                                //Text Formatting
                                var title = it.title
                                var writter = "Marvel"
                                var price = "Free"
                                if (title.length > 26)
                                    title = title.subSequence(0, 24).toString() + "..."
                                if (it.creators.items.isNotEmpty())
                                    writter = it.creators.items[0].name
                                if (writter.length > 26)
                                    writter = writter.substring(0, 25).toString() + "..."

                                if (it.prices.isNotEmpty() && "" + it.prices[0].price != "0.0")
                                    price = "$ " + it.prices[0].price


                                var url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                                val painter = rememberAsyncImagePainter(model = url)
                                BookCard(
                                    Color(
                                        Random.nextInt(170, 255),
                                        Random.nextInt(170, 255),
                                        Random.nextInt(175, 255)
                                    ),
                                    painter,
                                    false,
                                    price = price,
                                    title = title,
                                    writter = writter
                                )
                            }
                        }
                    }
                }
                else
                {

                    val list = booksViewModel.infiniteNavelList.collectAsState().value[0].data.results
                    LazyRow()
                    {
                        items(list)
                        {
                            //Text Formatting
                            var title=it.title
                            var writter="Marvel"
                            var price="Free"
                            if(title.length>26)
                                title=title.subSequence(0,24).toString()+"..."
                            if(it.creators.items.isNotEmpty())
                                writter=it.creators.items[0].name
                            if(writter.length>26)
                                writter=writter.substring(0,25).toString()+"..."

                            if(it.prices.isNotEmpty() &&""+it.prices[0].price!="0.0")
                                price= "$ "+it.prices[0].price


                            var url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                            val painter = rememberAsyncImagePainter(model = url)
                            BookCard(Color(
                                Random.nextInt(170,255),
                                Random.nextInt(170,255),
                                Random.nextInt(175,255)
                            ), painter,false,price=price, title = title, writter = writter)
                        }
                    }
                }

   /*
                //Infinite Novel

                Spacer(modifier = Modifier.height(18.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Infinite Novel", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onSecondary))
                    Text(text = "See All", style = TextStyle(fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color(23, 212, 252)))
                }
                val infiniteNovelData = produceState<DataOrException<TradePaperBookData, Boolean, Exception>>(
                    initialValue = DataOrException(loading = true)
                ) {
                    value = booksViewModel.getInfiniteComic()
                }.value

                if (infiniteNovelData.loading == true)
                {
                    val painter= rememberAsyncImagePainter(model = R.color.white)
                    LazyRow()
                    {
                        items(100)
                        {
                            BookCard(Color(
                                Random.nextInt(170,255),
                                Random.nextInt(170,255),
                                Random.nextInt(175,255)
                            ), painter = painter,true)
                        }
                    }
                }
                else {
                    val list = infiniteNovelData.data!!.data.results
                    LazyRow()
                    {
                        items(list)
                        {

                            //Text Formatting
                            var title=it.title
                            var writter="Marvel"
                            var price="Free"
                            if(title.length>26)
                                title=title.subSequence(0,24).toString()+"..."
                            if(it.creators.items.isNotEmpty())
                                writter=it.creators.items[0].name
                            if(writter.length>26)
                                writter=writter.substring(0,25).toString()+"..."

                            if(it.prices.isNotEmpty() &&""+it.prices[0].price!="0.0")
                                price= "$ "+it.prices[0].price


                            var url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                            val painter = rememberAsyncImagePainter(model = url)
                            BookCard(Color(
                                Random.nextInt(170,255),
                                Random.nextInt(170,255),
                                Random.nextInt(175,255)
                            ), painter,false,price=price, title = title, writter = writter)
                        }
                    }
                }

*/
                //hardCover

                Spacer(modifier = Modifier.height(18.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "HardCover", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onSecondary))
                    Text(text = "See All", style = TextStyle(fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color(23, 212, 252)))
                }
                val hardCoverData = produceState<DataOrException<TradePaperBookData, Boolean, Exception>>(
                    initialValue = DataOrException(loading = true)
                ) {
                    value = booksViewModel.getHardCover()
                }.value
                if (hardCoverData.loading == true)
                {
                    val painter= rememberAsyncImagePainter(model = R.color.white)
                    LazyRow()
                    {
                        items(100)
                        {
                            BookCard(Color(
                                Random.nextInt(170,255),
                                Random.nextInt(170,255),
                                Random.nextInt(175,255)
                            ), painter = painter,true)
                        }
                    }
                }
                else {
                    val list = hardCoverData.data!!.data.results
                    LazyRow()
                    {
                        items(list)
                        {
                            //Text Formatting
                            var title=it.title
                            var writter="Marvel"
                            var price="Free"
                            if(title.length>26)
                                title=title.subSequence(0,24).toString()+"..."
                            if(it.creators.items.isNotEmpty())
                                writter=it.creators.items[0].name
                            if(writter.length>26)
                                writter=writter.substring(0,25).toString()+"..."

                            if(it.prices.isNotEmpty() &&""+it.prices[0].price!="0.0")
                                price= "$ "+it.prices[0].price


                            var url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                            val painter = rememberAsyncImagePainter(model = url)
                            BookCard(Color(
                                Random.nextInt(170,255),
                                Random.nextInt(170,255),
                                Random.nextInt(175,255)
                            ), painter,false,price=price, title = title, writter = writter)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))



                //Trade paperBack

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Trade PaperBack", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onSecondary))
                    Text(text = "See All", style = TextStyle(fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color(23, 212, 252)))
                }

                if(booksViewModel.tradePaperBookDataList.collectAsState().value.isEmpty())
                {
                    val tradePaperBookData = produceState<DataOrException<TradePaperBookData, Boolean, Exception>>(
                    initialValue = DataOrException(loading = true)
                ) {
                    value = booksViewModel.getTradePaperBook()
                }.value
                if (tradePaperBookData.loading == true)
                {
                    val painter= rememberAsyncImagePainter(model = R.color.white)
                    LazyRow()
                    {
                        items(100)
                        {
                            BookCard(Color(
                                Random.nextInt(170,255),
                                Random.nextInt(170,255),
                                Random.nextInt(175,255)
                            ), painter = painter,true)
                        }
                    }
                }
                else {
                    //val list = tradePaperBookData.data!!.data.results
                        val list=booksViewModel.tradePaperBookDataList.collectAsState().value[0].data.results
                    LazyRow()
                    {
                        items(list)
                        {
                            //Text Formatting
                            var title=it.title
                            var writter="Marvel"
                            var price="Free"
                            if(title.length>26)
                                title=title.subSequence(0,24).toString()+"..."
                            if(it.creators.items.isNotEmpty())
                                writter=it.creators.items[0].name
                            if(writter.length>26)
                                writter=writter.substring(0,25).toString()+"..."

                            if(it.prices.isNotEmpty() &&""+it.prices[0].price!="0.0")
                                price= "$ "+it.prices[0].price


                            var url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                            val painter = rememberAsyncImagePainter(model = url)
                            BookCard(Color(
                                Random.nextInt(170,255),
                                Random.nextInt(170,255),
                                Random.nextInt(175,255)
                            ), painter,false,price=price, title = title, writter = writter)
                        }
                    }
                }
                }
                else {

                    val list =
                        booksViewModel.tradePaperBookDataList.collectAsState().value[0].data.results
                    LazyRow()
                    {
                        items(list)
                        {
                            //Text Formatting
                            var title = it.title
                            var writter = "Marvel"
                            var price = "Free"
                            if (title.length > 26)
                                title = title.subSequence(0, 24).toString() + "..."
                            if (it.creators.items.isNotEmpty())
                                writter = it.creators.items[0].name
                            if (writter.length > 26)
                                writter = writter.substring(0, 25).toString() + "..."

                            if (it.prices.isNotEmpty() && "" + it.prices[0].price != "0.0")
                                price = "$ " + it.prices[0].price


                            var url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                            val painter = rememberAsyncImagePainter(model = url)
                            BookCard(
                                Color(
                                    Random.nextInt(170, 255),
                                    Random.nextInt(170, 255),
                                    Random.nextInt(175, 255)
                                ), painter, false, price = price, title = title, writter = writter
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))


/*


               //Trade paperBack

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Trade PaperBack", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onSecondary))
                    Text(text = "See All", style = TextStyle(fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color(23, 212, 252)))
                }
                val tradePaperBookData = produceState<DataOrException<TradePaperBookData, Boolean, Exception>>(
                    initialValue = DataOrException(loading = true)
                ) {
                    value = booksViewModel.getTradePaperBook()
                }.value
                if (tradePaperBookData.loading == true)
                {
                    val painter= rememberAsyncImagePainter(model = R.color.white)
                    LazyRow()
                    {
                        items(100)
                        {
                            BookCard(Color(
                                Random.nextInt(170,255),
                                Random.nextInt(170,255),
                                Random.nextInt(175,255)
                            ), painter = painter,true)
                        }
                    }
                }
                else {

                    val list = tradePaperBookData.data!!.data.results
                    LazyRow()
                    {
                        items(list)
                        {
                            //Text Formatting
                            var title=it.title
                            var writter="Marvel"
                            var price="Free"
                            if(title.length>26)
                                title=title.subSequence(0,24).toString()+"..."
                            if(it.creators.items.isNotEmpty())
                                writter=it.creators.items[0].name
                            if(writter.length>26)
                                writter=writter.substring(0,25).toString()+"..."

                            if(it.prices.isNotEmpty() &&""+it.prices[0].price!="0.0")
                                price= "$ "+it.prices[0].price


                            var url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                            val painter = rememberAsyncImagePainter(model = url)
                            BookCard(Color(
                                Random.nextInt(170,255),
                                Random.nextInt(170,255),
                                Random.nextInt(175,255)
                            ), painter,false,price=price, title = title, writter = writter)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))





                //Trade paperBack

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Trade PaperBack", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onSecondary))
                    Text(text = "See All", style = TextStyle(fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color(23, 212, 252)))
                }

                //booksViewModel.deleteTradePaperBack()


                val tradePaperBookData = produceState<DataOrException<TradePaperBookData, Boolean, Exception>>(
                    initialValue = DataOrException(loading = true)
                ) {
                    booksViewModel.insertTradePaperBook()
                }.value
                if (tradePaperBookData.loading == true)
                {
                    val painter= rememberAsyncImagePainter(model = R.color.white)
                    LazyRow()
                    {
                        items(100)
                        {
                            BookCard(Color(
                                Random.nextInt(170,255),
                                Random.nextInt(170,255),
                                Random.nextInt(175,255)
                            ), painter = painter,true)
                        }
                    }
                }
                else {

                    //val list=booksViewModel.tradePaperBookDataList.collectAsState().value[0].data.results
                    val list=tradePaperBookData.data!!.data.results
                        Log.d("BOOKS", "BooksScreen: $list")
                    LazyRow()
                    {
                        items(list)
                        {
                            //Text Formatting
                            var title=it.title
                            var writter="Marvel"
                            var price="Free"
                            if(title.length>26)
                                title=title.subSequence(0,24).toString()+"..."
                            if(it.creators.items.isNotEmpty())
                                writter=it.creators.items[0].name
                            if(writter.length>26)
                                writter=writter.substring(0,25).toString()+"..."

                            if(it.prices.isNotEmpty() &&""+it.prices[0].price!="0.0")
                                price= "$ "+it.prices[0].price


                            var url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                            val painter = rememberAsyncImagePainter(model = url)
                            BookCard(Color(
                                Random.nextInt(170,255),
                                Random.nextInt(170,255),
                                Random.nextInt(175,255)
                            ), painter,false,price=price, title = title, writter = writter)
                        }
                    }

                }





                Spacer(modifier = Modifier.height(18.dp))

*/


                //Digest

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Digest", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onSecondary))
                    Text(text = "See All", style = TextStyle(fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color(23, 212, 252)))
                }
                val digestData = produceState<DataOrException<TradePaperBookData, Boolean, Exception>>(
                    initialValue = DataOrException(loading = true)
                ) {
                    value = booksViewModel.getDigest()
                }.value
                if (digestData.loading == true)
                {
                    val painter= rememberAsyncImagePainter(model = R.color.white)
                    LazyRow()
                    {
                        items(100)
                        {
                            BookCard(Color(
                                Random.nextInt(170,255),
                                Random.nextInt(170,255),
                                Random.nextInt(175,255)
                            ), painter = painter,true)
                        }
                    }
                }
                else {
                    val list = digestData.data!!.data.results
                    LazyRow()
                    {
                        items(list)
                        {
                            //Text Formatting
                            var title=it.title
                            var writter="Marvel"
                            var price="Free"
                            if(title.length>26)
                                title=title.subSequence(0,24).toString()+"..."
                            if(it.creators.items.isNotEmpty())
                                writter=it.creators.items[0].name
                            if(writter.length>26)
                                writter=writter.substring(0,25).toString()+"..."

                            if(it.prices.isNotEmpty() &&""+it.prices[0].price!="0.0")
                                price= "$ "+it.prices[0].price


                            var url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                            val painter = rememberAsyncImagePainter(model = url)
                            BookCard(Color(
                                Random.nextInt(170,255),
                                Random.nextInt(170,255),
                                Random.nextInt(175,255)
                            ), painter,false,price=price, title = title, writter = writter)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))


                //Graphic Novel

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Graphic Novel", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onSecondary))
                    Text(text = "See All", style = TextStyle(fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color(23, 212, 252)))
                }
                val graphicNovelData = produceState<DataOrException<TradePaperBookData, Boolean, Exception>>(
                    initialValue = DataOrException(loading = true)
                ) {
                    value = booksViewModel.getGraphicNovel()
                }.value
                if (graphicNovelData.loading == true)
                {
                    val painter= rememberAsyncImagePainter(model = R.color.white)
                    LazyRow()
                    {
                        items(100)
                        {
                            BookCard(Color(
                                Random.nextInt(170,255),
                                Random.nextInt(170,255),
                                Random.nextInt(175,255)
                            ), painter = painter,true)
                        }
                    }
                }
                else {
                    val list = graphicNovelData.data!!.data.results
                    LazyRow()
                    {
                        items(list)
                        {
                            //Text Formatting
                            var title=it.title
                            var writter="Marvel"
                            var price="Free"
                            if(title.length>26)
                                title=title.subSequence(0,24).toString()+"..."
                            if(it.creators.items.isNotEmpty())
                                writter=it.creators.items[0].name
                            if(writter.length>26)
                                writter=writter.substring(0,25).toString()+"..."

                            if(it.prices.isNotEmpty() &&""+it.prices[0].price!="0.0")
                                price= "$ "+it.prices[0].price


                            var url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                            val painter = rememberAsyncImagePainter(model = url)
                            BookCard(Color(
                                Random.nextInt(170,255),
                                Random.nextInt(170,255),
                                Random.nextInt(175,255)
                            ), painter,false,price=price, title = title, writter = writter)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(85.dp))

            }
        }
    }


    /*DB
    if(booksViewModel.tradePaperBookDataList.value.isEmpty())
        Log.d("BOOKS", " Empty TradePaperBookDataList")
    else
        Log.d("BOOKS", "TradePaperBookDataList = ${booksViewModel.tradePaperBookDataList.value}")

    if(booksViewModel.digestDataList.value.isEmpty())
        Log.d("BOOKS", " Empty DigestDataList")
    else
        Log.d("BOOKS", "TradePaperBookDataList = ${booksViewModel.digestDataList.value}")

     */

}