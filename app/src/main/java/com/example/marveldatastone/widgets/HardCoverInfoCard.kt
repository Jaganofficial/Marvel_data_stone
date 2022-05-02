package com.example.marveldatastone.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.scaleMatrix
import com.example.marveldatastone.R


@Composable
fun HardCoverInfoCard(title:String, painter: Painter,writer:String,price:String, originalPrice:String, formate:String , description:String,pageCount:String,language:String,detailUrl:String){
    var scrollState= rememberScrollState()
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)) {
        Text(text = "Star Wars Legends: The Empire Omnibus Vol. 1", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black, textAlign = TextAlign.Center), modifier = Modifier.padding(10.dp))
        Row(modifier=Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically)
        {
            Card(modifier = Modifier
                .height(200.dp)
                .width(150.dp)
                .padding(10.dp), elevation = 15.dp, shape = RoundedCornerShape(15.dp)) {
                Image(painter = painterResource(id = R.drawable.marvel_comics), contentDescription = "Image", contentScale = ContentScale.Crop)
            }
            Column( modifier = Modifier.fillMaxHeight(),verticalArrangement = Arrangement.SpaceEvenly) {
                Text(text = "Jeff Youngquist",style= TextStyle(color=Color.Gray, fontSize = 16.sp), modifier = Modifier.padding(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    Text(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                Color(221, 104, 41, 255),
                                fontSize = 26.sp,
                            )
                        )
                        {
                            append("$16.47 ")
                        }
                    }, color = Color.White, fontSize=16.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(10.dp))
                    Text(text = "$23.85", style = TextStyle(Color.Gray, fontSize = 16.sp), textDecoration = TextDecoration.LineThrough)
                }
                Text(text = "Format: HardCover", style = TextStyle(Color.Gray, fontSize = 16.sp), modifier = Modifier.padding(horizontal = 10.dp))
            }
            Spacer(modifier = Modifier.width(15.dp))
        }

        Divider(modifier = Modifier.padding(10.dp))

    }
}