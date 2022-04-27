package com.example.marveldatastone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.Coil
import com.example.marveldatastone.data.DataOrException
import com.example.marveldatastone.model.MarvelCharacterData
import com.example.marveldatastone.screens.Main.MainViewModel

@Composable
fun MarvelMainScreen(navController: NavController, mainViewModel: MainViewModel) {

    ShowData(mainViewModel)

}

@Composable
fun ShowData(mainViewModel: MainViewModel) {

    val characterData= produceState<DataOrException<MarvelCharacterData,Boolean,Exception>>(initialValue = DataOrException(loading = true)){
        value=mainViewModel.getCharacterData()
    }.value

    if(characterData.loading==true)
        Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    else
    {
        val textState= remember{
            mutableStateOf(0)
        }
        Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(modifier = Modifier.padding(15.dp), textStyle = TextStyle(color = Color.White), value = ""+textState.value , onValueChange ={
                    textState.value=it.toInt()
                } )
                Text(text = "It's ${characterData.data!!.data.results[textState.value].name}", style = TextStyle(color = Color.White))
            }
        }
    }
}
