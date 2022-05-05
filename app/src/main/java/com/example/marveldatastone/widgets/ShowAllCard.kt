package com.example.marveldatastone.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun ShowAllCard(painter: Painter,title:String,writer:String,price:String,modifier: Modifier=Modifier) {

    val x = remember {
        mutableStateOf(Random.nextInt(170, 255))
    }
    val y=remember {
        mutableStateOf(Random.nextInt(170, 255))
    }
    val z=remember {
        mutableStateOf(Random.nextInt(170, 255))
    }
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp, horizontal = 10.dp), elevation = 10.dp, shape = RoundedCornerShape(25.dp), backgroundColor  =  Color(
        x.value,y.value,z.value
    )
    ) {
        Column() {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {
                Card(modifier = Modifier
                    .height(220.dp)
                    .width(150.dp)
                    .padding(10.dp), elevation = 15.dp, shape = RoundedCornerShape(15.dp)) {
                    Image(painter = painter, contentDescription = "$title Image", contentScale = ContentScale.Crop)
                }
                Column() {
                    Text(text = title, style = TextStyle(color = MaterialTheme.colors.onSecondary, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold), modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp))
                    Text(text = writer,style = TextStyle(Color.Gray, fontSize = 17.sp), modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp))
                    Text(
                        text = "Price: $price",
                        style = TextStyle(
                            Color.Gray,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.ExtraBold,
                        ), modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp)
                    )
                }

            }
            Box(contentAlignment = Alignment.BottomStart) {
                Card(modifier = Modifier
                    .align(Alignment.Center), elevation = 15.dp, shape = RoundedCornerShape(bottomStart = 25.dp, topEnd = 25.dp), backgroundColor =  Color(
                    x.value-34,y.value-34,z.value-34
                )) {
                    Row() {
                        Spacer(modifier = Modifier.height(15.dp))
                        Spacer(modifier = Modifier.width(45.dp))
                        Text(
                            text = "See",
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 26.sp
                            )
                        )
                        Spacer(modifier = Modifier.width(45.dp))
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }
        }
       
    }
    
}