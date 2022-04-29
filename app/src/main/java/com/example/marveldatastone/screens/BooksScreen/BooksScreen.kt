package com.example.marveldatastone.screens.BooksScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
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
import com.example.marveldatastone.R
import com.example.marveldatastone.widgets.BookCard
import okhttp3.internal.wait
import kotlin.random.Random

@Preview
@Composable
fun BooksScreen (){//TODO: navController: NavController add this
    Surface(modifier = Modifier.fillMaxSize()) {
        var scrollableState= rememberScrollState()
        Column(modifier = Modifier
            .fillMaxSize().verticalScroll(scrollableState),verticalArrangement = Arrangement.SpaceEvenly) {
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
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Trade PaperBack", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onSecondary))
                    Text(text = "See All", style = TextStyle(fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color(23, 212, 252)))
                }
                LazyRow()
                {
                    items(100)
                    {
                        BookCard(Color(
                            Random.nextInt(170,255),
                            Random.nextInt(170,255),
                            Random.nextInt(175,255)
                        ), painterResource(id = R.drawable.marvel_comics))
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Trade PaperBack", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onSecondary))
                    Text(text = "See All", style = TextStyle(fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color(23, 212, 252)))
                }
                LazyRow()
                {
                    items(100)
                    {
                        BookCard(Color(77, 194, 71,500), painterResource(id = R.drawable.marvel_comics))
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Trade PaperBack", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onSecondary))
                    Text(text = "See All", style = TextStyle(fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color(23, 212, 252)))
                }
                LazyRow()
                {
                    items(100)
                    {
                        BookCard(Color(77, 194, 71,500), painterResource(id = R.drawable.marvel_comics))
                    }
                }

            }
        }
    }
}