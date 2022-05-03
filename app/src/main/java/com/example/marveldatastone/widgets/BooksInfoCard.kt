package com.example.marveldatastone.widgets

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BooksInfoCard(painter: Painter,title:String,pageCount:String,formate:String,price:String,actualPrice:String,description:String,modifier: Modifier =Modifier) {
    Surface(modifier = modifier) {
        val scrollState= rememberScrollState()
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp), contentAlignment = Alignment.TopStart)
                {
                   Card(modifier=Modifier.fillMaxWidth()) {
                       Image(painter = painter, contentDescription ="Image", contentScale = ContentScale.Crop )
                   }
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomStart){
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Color.Transparent,
                                        Color.Black
                                    ), startY = 100f
                                )
                            ))
                    }
                }
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart){
                    Row(modifier = Modifier.padding(25.dp), verticalAlignment = Alignment.CenterVertically) {
                        Card(modifier = Modifier
                            .height(260.dp)
                            .width(180.dp), shape = RoundedCornerShape(15.dp)) {
                            Image(painter = painter, contentDescription = "Image", contentScale = ContentScale.Crop)
                        }
                        Card(modifier = Modifier.offset(x=25.dp), shape = RoundedCornerShape(100.dp), contentColor = Color.Red, backgroundColor = Color(
                            231,
                            63,
                            63,
                            255
                        ), elevation = 15.dp) {
                            Text(text = "Buy Now", style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 21.sp), modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp))
                        }
                    }
                }
            }

            Column(modifier = Modifier.padding(horizontal = 25.dp)) {

                //Title
                Text(text = title, style = TextStyle(color = MaterialTheme.colors.onSecondary, fontWeight = FontWeight.Bold, fontSize = 21.sp,))

                //PageCount
                Row(modifier = Modifier.padding(vertical = 10.dp)) {
                    Text(text = "Page count: $pageCount", style = TextStyle(color = Color.Gray, fontSize = 16.sp))
                    Spacer(modifier = modifier.width(24.dp))
                    Text(text = "Formate: $formate",style = TextStyle(color = Color.Gray, fontSize = 16.sp))
                }


                //Price

                Row(modifier = Modifier.padding(vertical = 10.dp)) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    Color(221, 104, 41, 255),
                                    fontSize = 26.sp,
                                )
                            )
                            {
                                append("$price")
                            }
                        },
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(
                        text = "$actualPrice" ,
                        style = TextStyle(Color.Gray, fontSize = 16.sp),
                        textDecoration = TextDecoration.LineThrough
                    )
                }

                val readMoreDescription= remember {
                    mutableStateOf(false)
                }
                //Description
                Text(text = "Description", style = TextStyle(color = MaterialTheme.colors.onSecondary, fontWeight = FontWeight.Bold, fontSize = 19.sp,))
                Spacer(modifier = Modifier.height(5.dp))

                if(description.length>100 && !readMoreDescription.value)
                {
                    Text(text = description.substring(0,100)+"... ", style = TextStyle(color = Color.Gray, fontSize = 18.sp))
                    Text(text = "Read More", style =TextStyle(color = Color.Gray, fontSize = 19.sp, fontWeight = FontWeight.Bold), modifier = Modifier.clickable {
                        readMoreDescription.value=true
                    })
                }
                else if(description.length>100 && readMoreDescription.value)
                {
                    Text(text = description, style = TextStyle(color = Color.Gray, fontSize = 18.sp))
                    Text(text = "Show Less", style =TextStyle(color = Color.Gray, fontSize = 19.sp, fontWeight = FontWeight.Bold), modifier = Modifier.clickable {
                        readMoreDescription.value=false
                    })
                }
                else
                {
                    Text(text = description, style = TextStyle(color = Color.Gray, fontSize = 18.sp))
                }
            }
            Spacer(modifier = Modifier.height(150.dp))
        }
    }
}