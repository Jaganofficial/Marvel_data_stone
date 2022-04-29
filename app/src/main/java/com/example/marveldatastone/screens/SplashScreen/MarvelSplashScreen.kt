package com.example.marveldatastone.screens


import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.marveldatastone.R
import com.example.marveldatastone.navigation.MarvelDataNavigation
import com.example.marveldatastone.navigation.MarvelDataScreens
import kotlinx.coroutines.delay


@Composable
fun MarvelSplashScreen(navController: NavController) {
    //SplashScreen

    AsyncImage(model = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg", contentDescription = "Image", modifier = Modifier.fillMaxSize().border(5.dp,
        Color.Black))

    val scale1=remember{
        Animatable(0f);
    }
    val scale2=remember{
        Animatable(0f);
    }
    val scale3=remember{
        Animatable(0f);
    }
    val scale4=remember{
        Animatable(0f);
    }
    val scale5=remember{
        Animatable(0f);
    }
    LaunchedEffect(key1 = true )
    {
       /*
        scale1.animateTo(targetValue = 1f, animationSpec = tween(700, easing = {
            OvershootInterpolator(2f).getInterpolation(it)
        }))
        delay(100)
        scale2.animateTo(targetValue = 1f, animationSpec = tween(500, easing = {
            OvershootInterpolator(2f).getInterpolation(it)
        }))
        delay(100)
        scale3.animateTo(targetValue = 1f, animationSpec = tween(500, easing = {
            OvershootInterpolator(2f).getInterpolation(it)
        }))
        delay(100)
        scale4.animateTo(targetValue = 1f, animationSpec = tween(500, easing = {
            OvershootInterpolator(2f).getInterpolation(it)
        }))
        delay(100)
        scale5.animateTo(targetValue = 1f, animationSpec = tween(500, easing = {
            OvershootInterpolator(2f).getInterpolation(it)
        }))
        delay(1500)

        */

        navController.popBackStack()
        navController.navigate(MarvelDataScreens.MainScreen.name)
    }


    Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
            Surface() {
                Image(painter = painterResource(id = R.drawable.splashscreen6), contentDescription ="Logo", contentScale = ContentScale.Fit)
            }
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Surface(
                    modifier = Modifier
                        .size(65.dp)
                        .clip(CircleShape)
                        .padding(5.dp)
                        .scale(scale1.value)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.splashscreen1),
                        contentDescription = "Logo",
                        contentScale = ContentScale.Crop
                    )
                }
                Surface(
                    modifier = Modifier
                        .size(65.dp)
                        .clip(CircleShape)
                        .padding(5.dp)
                        .scale(scale2.value)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.splashscreen2),
                        contentDescription = "Logo",
                        contentScale = ContentScale.Crop
                    )
                }
                Surface(
                    modifier = Modifier
                        .size(65.dp)
                        .clip(CircleShape)
                        .padding(5.dp)
                        .scale(scale3.value)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.splashscreen3),
                        contentDescription = "Logo",
                        contentScale = ContentScale.Crop
                    )
                }
                Surface(
                    modifier = Modifier
                        .size(65.dp)
                        .clip(CircleShape)
                        .padding(5.dp)
                        .scale(scale4.value)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.splashscreen4),
                        contentDescription = "Logo",
                        contentScale = ContentScale.Crop
                    )
                }
                Surface(
                    modifier = Modifier
                        .size(65.dp)
                        .clip(CircleShape)
                        .padding(5.dp)
                        .scale(scale5.value)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.splashscreen5),
                        contentDescription = "Logo",
                        contentScale = ContentScale.Crop
                    )
                }

            }
        }
    }
}