package com.example.marveldatastone.screens.ShowAll.ShowAllComics.GraphicNovel

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.marveldatastone.R
import com.example.marveldatastone.navigation.MarvelDataScreens
import com.example.marveldatastone.screens.SharedViewModel.SharedViewModel
import com.example.marveldatastone.widgets.ShowAllCard


@Composable
fun ShowAllGraphicNovel(
    showAllGraphicNovelViewModel: ShowAllGraphicNovelViewModel,
    sharedViewModel: SharedViewModel,
    navController: NavController
) {

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            Color(47, 217, 183, 255),
                            fontSize = 37.sp,
                        )
                    )
                    {
                        append("Graphic")
                    }
                    append(" Novel")
                },
                color = MaterialTheme.colors.primaryVariant,
                fontSize = 35.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(15.dp))
            var list =
                emptyList<com.example.marveldatastone.model.CharacterModels.GraphicNovel.Result>()

            if (showAllGraphicNovelViewModel.graphicNovelListVM.collectAsState().value.isNotEmpty()) {
                list =
                    showAllGraphicNovelViewModel.graphicNovelListVM.collectAsState().value[0].data.results
            }
            Column() {
                LazyColumn()
                {
                    items(list)
                    {
                        val url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                        val painter = rememberAsyncImagePainter(
                            model = url, placeholder = painterResource(
                                id = R.drawable.preloader
                            )
                        )
                        val title = it.title
                        var writter = "Marvel"
                        var price = "Free"
                        if (it.creators.items.isNotEmpty())
                            writter = it.creators.items[0].name
                        if (writter.length > 26)
                            writter = writter.substring(0, 25) + "..."
                        if (it.prices.isNotEmpty() && "" + it.prices[0].price != "0.0")
                            price = "$" + it.prices[0].price
                        ShowAllCard(
                            painter = painter,
                            title = title,
                            writer = writter,
                            price = price, modifier = Modifier.clickable {
                                sharedViewModel.addGraphicNovelResult(it)
                                navController.navigate(MarvelDataScreens.BooksInfoScreen.name)
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(150.dp))
            }

        }

    }

}
