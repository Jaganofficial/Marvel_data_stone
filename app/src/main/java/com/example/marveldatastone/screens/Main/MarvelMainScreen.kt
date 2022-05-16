package com.example.marveldatastone.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.example.marveldatastone.R
import com.example.marveldatastone.data.DataOrException
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.MarvelCharacterData
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
import com.example.marveldatastone.navigation.MarvelDataScreens
import com.example.marveldatastone.screens.Main.MainViewModel
import com.example.marveldatastone.screens.SharedViewModel.SharedViewModel
import com.example.marveldatastone.widgets.GradelScreen
import com.example.marveldatastone.widgets.ImageCard
import com.example.marveldatastone.widgets.SearchBar

@Composable
fun MarvelMainScreen(navController: NavController, mainViewModel: MainViewModel,sharedViewModel: SharedViewModel) {
    ShowData(navController,mainViewModel, sharedViewModel =sharedViewModel )
}

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalCoilApi::class)
@Composable
fun ShowData(navController: NavController,mainViewModel: MainViewModel,sharedViewModel: SharedViewModel) {


    val scrollableState= rememberScrollState()
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(state = scrollableState), horizontalAlignment = Alignment.CenterHorizontally) {

        Card(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp), shape = RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp)) {
            Box()
            {

                Box()
                {
                    Image(
                        painter = painterResource(id =R.drawable.marvel_home_screen),
                        contentDescription = "Marvel Comics",
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Color.Transparent,
                                        Color.Transparent,
                                        Color.White
                                    )
                                )
                            )
                    )
                }
                
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween)
                {

                    Text(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                Color(255, 44, 44, 255),
                                fontSize = 40.sp,
                            )
                        )
                        {
                            append("M")
                        }
                        append("arvel Comics")
                    }, color = Color.White, fontSize=35.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.ExtraBold, modifier = Modifier.offset(15.dp))

                    Spacer(modifier = Modifier.height(15.dp))

                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 24.dp, vertical = 5.dp)
                        .clickable { navController.navigate(MarvelDataScreens.SearchScreen.name) }, shape = RoundedCornerShape(25.dp), backgroundColor = Color.White) {
                        Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                            Spacer(modifier = Modifier.width(15.dp))
                            Image(imageVector = Icons.Default.Search, contentDescription ="Books Search Image", modifier = Modifier.size(40.dp))
                            Text(text = "Search comics...", textAlign = TextAlign.Center, style = TextStyle(color = Color.Gray, fontSize = 14.sp))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Trending Now",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primaryVariant
                )
            )
            Text(
                text = "See All",
                style = TextStyle(
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(23, 212, 252)
                ), modifier = Modifier.clickable {
                    navController.navigate(MarvelDataScreens.ShowAllComics.name)
                }
            )
        }
        
        Spacer(modifier = Modifier.height(25.dp))



        if(mainViewModel.comicsList.value.isNotEmpty())
        {
            val list = mainViewModel.comicsList.value[0].data.results

            produceState<DataOrException<ComicsData, Boolean, Exception>>(
                initialValue = DataOrException(loading = true)
            ) {
                value = mainViewModel.getComics()
            }.value

            LazyRow(modifier = Modifier.fillMaxWidth())
            {
                items(list)
                {
                    val url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                    val writers :String
                    if(it.creators.items.isNullOrEmpty())
                    {
                        writers="Marvel"
                    }
                    else
                    {
                        writers=it.creators.items[0].name
                    }
                    val painter = rememberAsyncImagePainter(model = url, placeholder = painterResource(
                        id = R.drawable.preloader
                    ))
                    ImageCard(modifier = Modifier
                        .height(410.dp)
                        .width(250.dp)
                        .clickable {
                            sharedViewModel.addComicsResult(it)
                            navController.navigate(MarvelDataScreens.BooksInfoScreen.name)
                        }, title = it.title , painter = painter , desc ="Image" , fontsize = 19, writer = writers

                    )
                }
            }
        }
        else
        {
            //Comics
            val comicsData = produceState<DataOrException<ComicsData, Boolean, Exception>>(
                initialValue = DataOrException(loading = true)
            ) {
                value = mainViewModel.getComics()
            }.value


            if (comicsData.loading == true)
                Surface(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }

            else {

                if (comicsData.e == null) {
                    val list = comicsData.data!!.data.results

                    LazyRow(modifier = Modifier.fillMaxWidth())
                    {
                        items(list)
                        {
                            val url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                            val painter = rememberAsyncImagePainter(model = url, placeholder = painterResource(
                                id = R.drawable.preloader
                            ))
                            //Creators
                            val writers :String
                            if(it.creators.items.isNullOrEmpty())
                            {
                                writers="Marvel"
                            }
                            else
                            {
                                writers=it.creators.items[0].name
                            }
                            ImageCard(modifier = Modifier
                                .height(440.dp)
                                .width(280.dp)
                                .clickable {
                                    sharedViewModel.addComicsResult(it)
                                    navController.navigate(MarvelDataScreens.BooksInfoScreen.name)
                                },
                                title = it.title,
                                painter = painter,
                                desc = "Image",
                                fontsize = 19,
                                 writer = writers
                            )
                        }
                    }
                }
                else
                {
                    Box(modifier = Modifier.fillMaxWidth().padding(15.dp))
                    {
                        Text(text = "Oops! Something went wrong! Please check your internet connectivity", textAlign = TextAlign.Center, color = MaterialTheme.colors.primaryVariant)
                    }
                }
            }
            
        }
        Spacer(modifier = Modifier.height(105.dp))
    }
}
