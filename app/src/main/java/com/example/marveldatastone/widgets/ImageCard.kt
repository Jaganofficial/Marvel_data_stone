package com.example.marveldatastone.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ImageCard(modifier: Modifier, title:String, painter: Painter,desc:String,fontsize:Int,writer:String) {
    Column() {
        Surface(modifier = Modifier.padding(horizontal = 15.dp)) {
            Card(modifier = modifier, elevation = 25.dp, shape = RoundedCornerShape(12.dp)) {
                Box(modifier = Modifier.fillMaxSize())
                {
                    Card(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painter,
                            contentDescription = desc,
                            contentScale = ContentScale.Crop
                        )
                    }

                    Box(
                        modifier = modifier
                            .background(
                                brush = Brush.verticalGradient(
                                    listOf(
                                        Color.Transparent, Color.Transparent, Color.Black
                                    )
                                )
                            )
                    )
                }
            }
        }
        Box(modifier = Modifier.width(270.dp).padding(horizontal = 10.dp), contentAlignment = Alignment.CenterStart)
        {

            Column() {
                Text(
                    text = title,
                    style = TextStyle(
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        fontSize = fontsize.sp
                    ), modifier = Modifier
                        .padding(5.dp)
                        .offset(10.dp)
                )

                Text(
                    text = writer,
                    style = TextStyle(
                        color = Color.LightGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = (fontsize-2).sp
                    ), modifier = Modifier
                        .padding(5.dp)
                        .offset(10.dp)
                )
            }
            }

    }
}