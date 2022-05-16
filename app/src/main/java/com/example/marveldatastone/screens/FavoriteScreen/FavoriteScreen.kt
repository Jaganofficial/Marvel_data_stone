package com.example.marveldatastone.screens.FavoriteScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.marveldatastone.R
import com.example.marveldatastone.model.CharacterModels.ComicsModels.Result
import com.example.marveldatastone.navigation.MarvelDataScreens
import com.example.marveldatastone.repository.DataStore.DataStoreRepository
import com.example.marveldatastone.repository.DataStore.DataStoreRepositoryViewModel
import com.example.marveldatastone.screens.SharedViewModel.DataStoreViewModelFactory
import com.example.marveldatastone.screens.SharedViewModel.SharedViewModel
import com.example.marveldatastone.screens.ShowAll.ShowAllComics.GraphicNovel.ShowAllGraphicNovelViewModel
import com.example.marveldatastone.screens.ShowAll.ShowAllComics.InfiniteNovelShowAll.ShowAllInfiniteNovelViewModel
import com.example.marveldatastone.screens.ShowAll.ShowAllComics.ShowAllComicsViewModel
import com.example.marveldatastone.screens.ShowAll.ShowAllComics.ShowAllDigest.ShowAllDigestViewModel
import com.example.marveldatastone.screens.ShowAll.ShowAllComics.ShowAllHardCover.ShowAllHardCoverViewModel
import com.example.marveldatastone.screens.ShowAll.ShowAllComics.ShowAlltradePaperback.ShowAllTradePaperBackViewModel
import com.example.marveldatastone.widgets.BookCard
import kotlin.random.Random

@Composable
fun FavoriteScreen(navController: NavController,dataStoreRepositoryViewModel: DataStoreRepositoryViewModel = viewModel(factory = DataStoreViewModelFactory(
    DataStoreRepository(LocalContext.current)
)),showAllComicsViewModel: ShowAllComicsViewModel,sharedViewModel: SharedViewModel,showAllHardCoverViewModel: ShowAllHardCoverViewModel,showAllInfiniteNovelViewModel: ShowAllInfiniteNovelViewModel,showAllTradePaperBackViewModel: ShowAllTradePaperBackViewModel,showAllDigestViewModel: ShowAllDigestViewModel,showAllGraphicNovelViewModel: ShowAllGraphicNovelViewModel) {

    //DataStore
    val getId = dataStoreRepositoryViewModel.favorites.observeAsState().value


    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Surface() {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier =
                Modifier.fillMaxWidth()
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                Color(255, 102, 102),
                                fontSize = 37.sp,
                            )
                        )
                        {
                            append("Favorites")
                        }
                    },
                    color = MaterialTheme.colors.primaryVariant,
                    fontSize = 35.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.height(15.dp))



                if (getId!="") {
                    var list = emptyList<Result>()

                    if (showAllComicsViewModel.comicsListVM.collectAsState().value.isNotEmpty()) {
                        list =
                            showAllComicsViewModel.comicsListVM.collectAsState().value[0].data.results
                    }

                    if (list.filter { getId!!.contains(it.id.toString() + ",") }.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Comics",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.primaryVariant
                                )
                            )
                        }
                        LazyRow()
                        {
                            items(list.filter { getId!!.contains(it.id.toString() + ",") })
                            {
                                //Text Formatting
                                var title = it.title
                                var writter = "Marvel"
                                var price = "Free"
                                if (title.length > 26)
                                    title = title.subSequence(0, 24).toString() + "..."
                                if (!it.creators.items.isNullOrEmpty())
                                    writter = it.creators.items[0].name
                                if (writter.length > 16)
                                    writter = writter.substring(0, 15) + "..."

                                if (!it.prices.isNullOrEmpty() && "" + it.prices[0].price != "0.0")
                                    price = "$ " + it.prices[0].price

                                val x by remember {
                                    mutableStateOf(Random.nextInt(170, 255))
                                }
                                val y by remember {
                                    mutableStateOf(Random.nextInt(170, 255))
                                }
                                val z by remember {
                                    mutableStateOf(Random.nextInt(170, 255))
                                }
                                val url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                                val painter = rememberAsyncImagePainter(
                                    model = url, placeholder = painterResource(
                                        id = R.drawable.preloader
                                    )
                                )
                                BookCard(
                                    Color(
                                        x, y, z
                                    ),
                                    painter,
                                    false,
                                    price = price,
                                    title = title,
                                    writter = writter,
                                    modifier = Modifier.clickable {
                                        sharedViewModel.addComicsResult(it)
                                        navController.navigate(MarvelDataScreens.BooksInfoScreen.name)
                                    }
                                )
                            }
                        }
                    }


                    var INlist =
                        emptyList<com.example.marveldatastone.model.CharacterModels.InfiniteNovel.Result>()

                    if (showAllInfiniteNovelViewModel.infiniteNovelListVM.collectAsState().value.isNotEmpty()) {
                        INlist =
                            showAllInfiniteNovelViewModel.infiniteNovelListVM.collectAsState().value[0].data.results
                    }


                    if (INlist.filter { getId!!.contains(it.id.toString() + ",") }.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Infinite Comics",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.primaryVariant
                                )
                            )
                        }
                        Column() {
                            LazyRow()
                            {
                                items(INlist.filter { getId!!.contains(it.id.toString() + ",") })
                                {
                                    //Text Formatting
                                    var title = it.title
                                    var writter = "Marvel"
                                    var price = "Free"
                                    if (title.length > 26)
                                        title = title.subSequence(0, 24).toString() + "..."
                                    if (!it.creators.items.isNullOrEmpty())
                                        writter = it.creators.items[0].name
                                    if (writter.length > 16)
                                        writter = writter.substring(0, 15) + "..."

                                    if (!it.prices.isNullOrEmpty() && "" + it.prices[0].price != "0.0")
                                        price = "$ " + it.prices[0].price

                                    val x by remember {
                                        mutableStateOf(Random.nextInt(170, 255))
                                    }
                                    val y by remember {
                                        mutableStateOf(Random.nextInt(170, 255))
                                    }
                                    val z by remember {
                                        mutableStateOf(Random.nextInt(170, 255))
                                    }
                                    val url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                                    val painter = rememberAsyncImagePainter(
                                        model = url, placeholder = painterResource(
                                            id = R.drawable.preloader
                                        )
                                    )
                                    BookCard(
                                        Color(
                                            x, y, z
                                        ),
                                        painter,
                                        false,
                                        price = price,
                                        title = title,
                                        writter = writter,
                                        modifier = Modifier.clickable {
                                            sharedViewModel.addInfiniteNovelResult(it)
                                            navController.navigate(MarvelDataScreens.BooksInfoScreen.name)
                                        }
                                    )
                                }
                            }

                        }
                    }


                    var HClist =
                        emptyList<com.example.marveldatastone.model.CharacterModels.HardCover.Result>()

                    if (showAllHardCoverViewModel.hardCoverListVM.collectAsState().value.isNotEmpty()) {
                        HClist =
                            showAllHardCoverViewModel.hardCoverListVM.collectAsState().value[0].data.results
                    }
                    if (HClist.filter { getId!!.contains(it.id.toString() + ",") }.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Hard Cover",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.primaryVariant
                                )
                            )
                        }
                    }
                    Column() {
                        LazyRow()
                        {
                            items(HClist.filter { getId!!.contains(it.id.toString() + ",") })
                            {
                                //Text Formatting
                                var title = it.title
                                var writter = "Marvel"
                                var price = "Free"
                                if (title.length > 26)
                                    title = title.subSequence(0, 24).toString() + "..."
                                if (!it.creators.items.isNullOrEmpty())
                                    writter = it.creators.items[0].name
                                if (writter.length > 16)
                                    writter = writter.substring(0, 15) + "..."

                                if (!it.prices.isNullOrEmpty() && "" + it.prices[0].price != "0.0")
                                    price = "$ " + it.prices[0].price

                                val x by remember {
                                    mutableStateOf(Random.nextInt(170, 255))
                                }
                                val y by remember {
                                    mutableStateOf(Random.nextInt(170, 255))
                                }
                                val z by remember {
                                    mutableStateOf(Random.nextInt(170, 255))
                                }
                                val url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                                val painter = rememberAsyncImagePainter(
                                    model = url, placeholder = painterResource(
                                        id = R.drawable.preloader
                                    )
                                )
                                BookCard(Color(
                                    x, y, z
                                ),
                                    painter,
                                    false,
                                    price = price,
                                    title = title,
                                    writter = writter,
                                    modifier = Modifier.clickable {
                                        sharedViewModel.addHardCoverResult(it)
                                        navController.navigate(MarvelDataScreens.BooksInfoScreen.name)
                                    })
                            }
                        }
                    }


                    var TPBlist =
                        emptyList<com.example.marveldatastone.model.CharacterModels.TradePaperBackModel.Result>()

                    if (showAllTradePaperBackViewModel.tradePaperBackListVM.collectAsState().value.isNotEmpty()) {
                        TPBlist =
                            showAllTradePaperBackViewModel.tradePaperBackListVM.collectAsState().value[0].data.results
                    }
                    if (TPBlist.filter { getId!!.contains(it.id.toString() + ",") }.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Trade PaperBack",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.primaryVariant
                                )
                            )
                        }
                    }
                    Column() {
                        LazyRow()
                        {
                            items(TPBlist.filter { getId!!.contains(it.id.toString() + ",") })
                            {
                                //Text Formatting
                                var title = it.title
                                var writter = "Marvel"
                                var price = "Free"
                                if (title.length > 26)
                                    title = title.subSequence(0, 24).toString() + "..."
                                if (!it.creators.items.isNullOrEmpty())
                                    writter = it.creators.items[0].name
                                if (writter.length > 16)
                                    writter = writter.substring(0, 15) + "..."

                                if (!it.prices.isNullOrEmpty() && "" + it.prices[0].price != "0.0")
                                    price = "$ " + it.prices[0].price

                                val x by remember {
                                    mutableStateOf(Random.nextInt(170, 255))
                                }
                                val y by remember {
                                    mutableStateOf(Random.nextInt(170, 255))
                                }
                                val z by remember {
                                    mutableStateOf(Random.nextInt(170, 255))
                                }
                                val url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                                val painter = rememberAsyncImagePainter(
                                    model = url, placeholder = painterResource(
                                        id = R.drawable.preloader
                                    )
                                )
                                BookCard(Color(
                                    x, y, z
                                ),
                                    painter,
                                    false,
                                    price = price,
                                    title = title,
                                    writter = writter,
                                    modifier = Modifier.clickable {
                                        sharedViewModel.addTradepaperbackResult(it)
                                        navController.navigate(MarvelDataScreens.BooksInfoScreen.name)
                                    })
                            }
                        }
                    }


                    var Dlist =
                        emptyList<com.example.marveldatastone.model.CharacterModels.Digest.Result>()

                    if (showAllDigestViewModel.digestListVM.collectAsState().value.isNotEmpty()) {
                        Dlist =
                            showAllDigestViewModel.digestListVM.collectAsState().value[0].data.results
                    }
                    if (Dlist.filter { getId!!.contains(it.id.toString() + ",") }.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Digest",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.primaryVariant
                                )
                            )
                        }
                    }
                    Column() {
                        LazyRow()
                        {
                            items(Dlist.filter { getId!!.contains(it.id.toString() + ",") })
                            {
                                //Text Formatting
                                var title = it.title
                                var writter = "Marvel"
                                var price = "Free"
                                if (title.length > 26)
                                    title = title.subSequence(0, 24).toString() + "..."
                                if (!it.creators.items.isNullOrEmpty())
                                    writter = it.creators.items[0].name
                                if (writter.length > 16)
                                    writter = writter.substring(0, 15)+ "..."

                                if (!it.prices.isNullOrEmpty() && "" + it.prices[0].price != "0.0")
                                    price = "$ " + it.prices[0].price

                                val x by remember {
                                    mutableStateOf(Random.nextInt(170, 255))
                                }
                                val y by remember {
                                    mutableStateOf(Random.nextInt(170, 255))
                                }
                                val z by remember {
                                    mutableStateOf(Random.nextInt(170, 255))
                                }
                                val url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                                val painter = rememberAsyncImagePainter(
                                    model = url, placeholder = painterResource(
                                        id = R.drawable.preloader
                                    )
                                )
                                BookCard(Color(
                                    x, y, z
                                ),
                                    painter,
                                    false,
                                    price = price,
                                    title = title,
                                    writter = writter,
                                    modifier = Modifier.clickable {
                                        sharedViewModel.addDigestResult(it)
                                        navController.navigate(MarvelDataScreens.BooksInfoScreen.name)
                                    })
                            }
                        }
                    }


                    var GNlist =
                        emptyList<com.example.marveldatastone.model.CharacterModels.GraphicNovel.Result>()

                    if (showAllGraphicNovelViewModel.graphicNovelListVM.collectAsState().value.isNotEmpty()) {
                        GNlist =
                            showAllGraphicNovelViewModel.graphicNovelListVM.collectAsState().value[0].data.results
                    }
                    if (GNlist.filter { getId!!.contains(it.id.toString() + ",") }.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Graphic Novel",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.primaryVariant
                                )
                            )
                        }
                    }
                    Column() {
                        LazyRow()
                        {
                            items(GNlist.filter { getId!!.contains(it.id.toString() + ",") })
                            {
                                //Text Formatting
                                var title = it.title
                                var writter = "Marvel"
                                var price = "Free"
                                if (title.length > 26)
                                    title = title.subSequence(0, 24).toString() + "..."
                                if (!it.creators.items.isNullOrEmpty())
                                    writter = it.creators.items[0].name
                                if (writter.length > 16)
                                    writter = writter.substring(0, 15) + "..."

                                if (!it.prices.isNullOrEmpty() && "" + it.prices[0].price != "0.0")
                                    price = "$ " + it.prices[0].price

                                val x by remember {
                                    mutableStateOf(Random.nextInt(170, 255))
                                }
                                val y by remember {
                                    mutableStateOf(Random.nextInt(170, 255))
                                }
                                val z by remember {
                                    mutableStateOf(Random.nextInt(170, 255))
                                }
                                val url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                                val painter = rememberAsyncImagePainter(
                                    model = url, placeholder = painterResource(
                                        id = R.drawable.preloader
                                    )
                                )
                                BookCard(Color(
                                    x, y, z
                                ),
                                    painter,
                                    false,
                                    price = price,
                                    title = title,
                                    writter = writter,
                                    modifier = Modifier.clickable {
                                        sharedViewModel.addGraphicNovelResult(it)
                                        navController.navigate(MarvelDataScreens.BooksInfoScreen.name)
                                    })
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(85.dp))
                }
                else
                {
                    Box(modifier = Modifier.fillMaxSize().padding(25.dp), contentAlignment = Alignment.Center)
                    {
                        Text(text = ("\"Hello!, Find all your favorite comics here. \n Click ♡ to add favorites :) \"")
                            , textAlign = TextAlign.Center, color = Color.Gray)
                    }
                }
            }
        }
    }
}