package com.example.marveldatastone.widgets

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marveldatastone.R
import java.util.*


@Composable
fun BooksInfoCard(painter: Painter,title:String,pageCount:String,formate:String,price:String,actualPrice:String,description:String,date:String,language:String,painters:List<Painter>,creators:List<String>,characters:List<String>,dataUrl:String,modifier: Modifier =Modifier) {
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
                            .width(180.dp), shape = RoundedCornerShape(15.dp), elevation = 45.dp) {
                            Image(painter = painter, contentDescription = "Image", contentScale = ContentScale.Crop)
                        }

                        val url=dataUrl
                        val context = LocalContext.current
                        val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(url)) }

                        Card(modifier = Modifier.offset(x=25.dp).clickable {
                            context.startActivity(intent)
                        }, shape = RoundedCornerShape(100.dp), contentColor = Color.Red, backgroundColor = Color(
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
                Text(text = title, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 21.sp,))

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
                Text(text = "Description", style = TextStyle( fontWeight = FontWeight.Bold, fontSize = 19.sp,))
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

                //Release Date

                Divider(modifier = Modifier.padding(vertical = 15.dp))

                Text(text = "Release date", style = TextStyle( fontSize = 19.sp))
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = date, style = TextStyle(color = Color.Gray, fontSize = 17.sp))

                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Language", style = TextStyle( fontSize = 19.sp))
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = language.uppercase(Locale.getDefault()), style = TextStyle(color = Color.Gray, fontSize = 17.sp))


                Divider(modifier = Modifier.padding(vertical = 15.dp))



            }

            Text(text = "Related Images", style = TextStyle( fontSize = 19.sp, fontWeight = FontWeight.SemiBold), modifier = Modifier.padding(15.dp))
            if(painters.isEmpty())
            {
                Text(text = "No preview Available", style = TextStyle(color = Color.Gray, fontSize = 17.sp), modifier = Modifier.padding(25.dp))
            }
            else
            {
                LazyRow()
                {
                    items(painters)
                    {
                        Card(modifier = Modifier
                            .height(320.dp)
                            .width(230.dp).padding(15.dp), shape = RoundedCornerShape(15.dp), elevation = 15.dp) {
                            Image(painter = it, contentDescription = "Image", contentScale = ContentScale.Crop)
                        }
                    }
                }
            }


            Text(text = "Creators ", style = TextStyle( fontSize = 19.sp, fontWeight = FontWeight.SemiBold), modifier = Modifier.padding(15.dp))
            if(creators.isNullOrEmpty())
            {
                Text(text = "Marvel", style = TextStyle(color = Color.Gray, fontSize = 17.sp), modifier = Modifier.padding(25.dp))
            }
            else
            {
                LazyRow()
                {
                    items(creators)
                    {
                        var cid:Int
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Card(modifier = Modifier
                                .height(100.dp)
                                .width(100.dp).padding(15.dp), shape = CircleShape, elevation = 15.dp) {
                                if(it.contains("colorist"))
                                    cid=R.drawable.colorist
                                else if(it.contains("inker"))
                                    cid=R.drawable.writer
                                else
                                    cid=R.drawable.writers

                                Image(painter = painterResource(id = cid), contentDescription = "Creators", contentScale = ContentScale.Crop)
                            }
                            Text(text = it, style = TextStyle(color = Color.Gray, fontSize = 17.sp, textAlign = TextAlign.Center), modifier = Modifier.padding(15.dp))
                        }
                    }
                }
            }



            //Characters

            Text(text = "Characters ", style = TextStyle( fontSize = 19.sp, fontWeight = FontWeight.SemiBold), modifier = Modifier.padding(15.dp))
            if(characters.isNullOrEmpty())
            {
                Text(text = "No details available", style = TextStyle(color = Color.Gray, fontSize = 17.sp), modifier = Modifier.padding(25.dp))
            }
            else
            {
                LazyRow(modifier = Modifier.fillMaxWidth())
                {
                    items(characters)
                    {
                        Surface( modifier = Modifier.padding(14.dp)) {
                            Card(shape = RoundedCornerShape(15.dp), elevation = 15.dp, contentColor = Color.Gray, backgroundColor = Color.Gray) {
                                Text(text = it, style = TextStyle(color = Color.White, fontSize = 21.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold), modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp))
                            }
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.height(150.dp))
        }
    }
}