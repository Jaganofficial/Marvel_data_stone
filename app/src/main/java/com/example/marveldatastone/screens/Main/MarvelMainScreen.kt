package com.example.marveldatastone.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.example.marveldatastone.data.DataOrException
import com.example.marveldatastone.model.CharacterModels.CharacteresModel.MarvelCharacterData
import com.example.marveldatastone.model.CharacterModels.ComicsModels.ComicsData
import com.example.marveldatastone.navigation.MarvelDataScreens
import com.example.marveldatastone.screens.Main.MainViewModel
import com.example.marveldatastone.widgets.GradelScreen
import com.example.marveldatastone.widgets.ImageCard
import com.example.marveldatastone.widgets.SearchBar

@Composable
fun MarvelMainScreen(navController: NavController, mainViewModel: MainViewModel) {
    ShowData(navController,mainViewModel)
}

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalCoilApi::class)
@Composable
fun ShowData(navController: NavController,mainViewModel: MainViewModel) {
    GradelScreen()
    Text(
        text = "Comics",
        modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp),
        style = TextStyle(color = Color.LightGray, fontWeight = FontWeight.Bold, fontSize = 35.sp)
    )



    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {


        SearchBar(modifier = Modifier.clickable {
            navController.navigate(MarvelDataScreens.ComicsSearchScreen.name)
        })


        if(mainViewModel.comicsList.value.isNotEmpty())
        {

            Log.d("MAIN1", "ShowData: From DB")
            val list = mainViewModel.comicsList.value[0].data.results
            LazyRow(modifier = Modifier.fillMaxWidth())
            {
                items(list)
                {
                    var url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                    val painter = rememberAsyncImagePainter(model = url)
                    ImageCard(modifier = Modifier
                        .height(440.dp)
                        .width(280.dp), title = it.title , painter = painter , desc ="Image" , fontsize = 22)
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

                val list = comicsData.data!!.data.results

                LazyRow(modifier = Modifier.fillMaxWidth())
                {
                    items(list)
                    {
                        var url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                        val painter = rememberAsyncImagePainter(model = url)
                        ImageCard(modifier = Modifier
                            .height(440.dp)
                            .width(280.dp), title = it.title , painter = painter , desc ="Image" , fontsize = 22)
                    }
                }
            }
        }


        /*
        if(mainViewModel.characterList.value.isNullOrEmpty())
        {


        }
        else
        {
            Box() {
                val list = mainViewModel.characterList.value.get(0).data!!.results
                LazyRow(modifier = Modifier.fillMaxWidth())
                {
                    items(list)
                    {
                        var url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                        val painter = rememberAsyncImagePainter(model = url)
                        ImageCard(modifier = Modifier
                            .height(420.dp)
                            .width(270.dp), title = it.name+"from DB" , painter = painter , desc ="Image" , fontsize = 22)
                    }
                }
            }
        }

         */
    }
}
