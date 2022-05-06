package com.example.marveldatastone.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import com.example.marveldatastone.R

@Composable
fun BookCard(color: Color,painter: AsyncImagePainter,showLoader: Boolean=true,title:String="",price:String="",writter:String="",modifier: Modifier=Modifier) {
    Surface(modifier = modifier) {
        Column(Modifier.width(260.dp)) {
            Surface(
                modifier = Modifier
                    .padding(15.dp)
            ) {
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier.padding(horizontal = 15.dp)
                )
                {
                    Card(
                        modifier = Modifier
                            .height(190.dp)
                            .width(220.dp),
                        backgroundColor = color,
                        contentColor = color,
                        shape = RoundedCornerShape(15.dp)
                    ) {

                    }
                    Box(
                        modifier = Modifier
                            .padding(vertical = 16.dp), contentAlignment = Alignment.BottomCenter
                    )
                    {
                        Card(
                            modifier = Modifier
                                .height(235.dp)
                                .width(165.dp), elevation = 15.dp, shape = RoundedCornerShape(12.dp)
                        ) {
                            if (showLoader)
                            {
                                Image(
                                    painter = painterResource(id = R.drawable.loading),
                                    contentDescription = "image",
                                    contentScale = ContentScale.Crop
                                )
                            }
                            else {
                                Image(
                                    painter = painter,
                                    contentDescription = "image",
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 25.dp)
                    .fillMaxWidth()
            )
            {
                Column() {
                    Text(
                        text = title,
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color =MaterialTheme.colors.primaryVariant)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    )
                    {
                        Text(
                            text = writter,
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = price,
                            style = TextStyle(
                                Color.Gray,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                    }
                }
            }
        }
    }
}