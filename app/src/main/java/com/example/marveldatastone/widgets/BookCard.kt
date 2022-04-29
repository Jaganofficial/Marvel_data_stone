package com.example.marveldatastone.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marveldatastone.R


@Composable
fun BookCard(color: Color,painter: Painter) {
    Surface(modifier = Modifier.padding(15.dp)) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.padding(horizontal = 15.dp)
        )
        {
            Card(
                modifier = Modifier
                    .height(190.dp)
                    .width(220.dp), backgroundColor = color, contentColor = color, shape = RoundedCornerShape(15.dp)
            ) {
            }
            Box(
                modifier = Modifier
                    .padding(vertical = 16.dp), contentAlignment = Alignment.BottomCenter
            )
            {
                Card(
                    modifier = Modifier
                        .height(220.dp)
                        .width(150.dp), elevation = 10.dp, shape = RoundedCornerShape(12.dp)
                ) {
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