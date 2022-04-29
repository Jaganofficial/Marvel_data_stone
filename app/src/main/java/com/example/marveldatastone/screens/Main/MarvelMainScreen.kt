package com.example.marveldatastone.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
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
import com.example.marveldatastone.screens.Main.MainViewModel
import com.example.marveldatastone.widgets.GradelScreen
import com.example.marveldatastone.widgets.ImageCard

@Composable
fun MarvelMainScreen(navController: NavController, mainViewModel: MainViewModel) {
    ShowData(mainViewModel)
}

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalCoilApi::class)
@Composable
fun ShowData(mainViewModel: MainViewModel) {
    GradelScreen()
    Text(
        text = "Comics",
        modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp),
        style = TextStyle(color = Color.LightGray, fontWeight = FontWeight.Bold, fontSize = 35.sp)
    )

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {


        if(mainViewModel.characterList.value.isNullOrEmpty())
        {

        }

        //Comics
        val comicsData = produceState<DataOrException<MarvelCharacterData, Boolean, Exception>>(
            initialValue = DataOrException(loading = true)
        ) {
            value = mainViewModel.getCharacterData()
            Log.d("FromMarvelMainScreen", "characterList: ${mainViewModel.characterList.value}")
        }.value


        if (comicsData.loading == true)
            Surface(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
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
                        .height(420.dp)
                        .width(270.dp), title = it.name , painter = painter , desc ="Image" , fontsize = 22)
                }
            }
        }
    }
}





/*
   Column(modifier = Modifier.fillMaxSize()) {

       Text(
           text = "Comics",
           modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp),
           style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 24.sp)
       )

       Surface(modifier = Modifier.fillMaxWidth()) {
           //Comics
           val comicsData = produceState<DataOrException<ComicsData, Boolean, Exception>>(
               initialValue = DataOrException(loading = true)
           ) {
               value = mainViewModel.getComics()
           }.value


           if (comicsData.loading == true)
               Surface(modifier = Modifier.fillMaxWidth()) {
                   Column(
                       modifier = Modifier.fillMaxWidth(),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally
                   ) {
                       CircularProgressIndicator()
                   }
               }
           else {

               Surface(modifier = Modifier.fillMaxWidth()) {
                   Column(
                       modifier = Modifier.fillMaxWidth(),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally
                   ) {
                       val list = comicsData.data!!.data.results
                       LazyRow(modifier =  Modifier.fillMaxWidth())
                       {
                           items(list)
                           {
                               var url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                               val painter = rememberAsyncImagePainter(model = url)
                               Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                   Surface(
                                       shape = RoundedCornerShape(15.dp),
                                       modifier = Modifier
                                           .height(320.dp)
                                           .width(210.dp)
                                           .padding(horizontal = 5.dp, vertical = 5.dp),
                                       elevation = 15.dp
                                   ) {
                                       Image(
                                           painter = painter,
                                           contentDescription = "Image",
                                           contentScale = ContentScale.Crop
                                       )
                                   }
                                   Text(
                                       text = it.title,
                                       style = TextStyle(
                                           color = Color.Black,
                                           fontWeight = FontWeight.Bold,
                                           fontSize = 15.sp
                                       ), modifier = Modifier.padding(5.dp)
                                   )
                               }
                           }
                       }
                   }
               }
           }
       }

       Text(
           text = "Characters",
           modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp),
           style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 24.sp)
       )

       Surface(modifier = Modifier.fillMaxWidth()) {
           //Characters
           val characterData =
               produceState<DataOrException<MarvelCharacterData, Boolean, Exception>>(
                   initialValue = DataOrException(loading = true)
               ) {
                   value = mainViewModel.getCharacterData()
               }.value



           if (characterData.loading == true)
               Surface(modifier = Modifier.fillMaxWidth()) {
                   Column(
                       modifier = Modifier.fillMaxWidth(),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally
                   ) {
                       CircularProgressIndicator()
                   }
               }
           else {

               Surface(modifier = Modifier.fillMaxWidth()) {
                   Column(
                       modifier = Modifier.fillMaxWidth(),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally
                   ) {
                       val list = characterData.data!!.data.results
                       LazyRow()
                       {
                           items(list)
                           {
                               var url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                               val painter = rememberAsyncImagePainter(model = url)
                               Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                   Surface(
                                       shape = RoundedCornerShape(15.dp),
                                       modifier = Modifier
                                           .height(320.dp)
                                           .width(210.dp)
                                           .padding(horizontal = 5.dp, vertical = 5.dp),
                                       elevation = 15.dp
                                   ) {
                                       Image(
                                           painter = painter,
                                           contentDescription = "Image",
                                           contentScale = ContentScale.Crop
                                       )
                                   }
                                   Text(
                                       text = it.name,
                                       style = TextStyle(
                                           color = Color.Black,
                                           fontWeight = FontWeight.Bold,
                                           fontSize = 15.sp
                                       )
                                   )
                               }
                           }
                       }
                   }
               }
           }
       }
   }*/
