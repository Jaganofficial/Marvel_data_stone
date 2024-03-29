package com.example.marveldatastone.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.marveldatastone.R
import com.example.marveldatastone.navigation.MarvelDataScreens
import kotlinx.coroutines.delay


@Composable
fun MarvelSplashScreen(navController: NavController) {
    //SplashScreen

    LaunchedEffect(key1 = true)
    {
        delay(1500)
        navController.popBackStack()
        navController.navigate(MarvelDataScreens.MainScreen.name)
    }


    Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface() {
                Image(
                    painter = painterResource(id = R.drawable.splashscreen6),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Fit
                )
            }
            Surface(modifier = Modifier.height(250.dp), color = Color.Transparent) {

            }
        }
    }
}