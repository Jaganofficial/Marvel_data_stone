package com.example.marveldatastone.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun SearchBar(modifier: Modifier=Modifier) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(110.dp)
        .padding(25.dp)
        .background(Color.Transparent), shape = RoundedCornerShape(35.dp), elevation = 15.dp) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.horizontalGradient(
                    listOf(
                        Color.Black,
                        Color.Gray,
                        //Color(155, 19, 6)
                    )
                )
            ), contentAlignment = Alignment.CenterStart){

            Row(modifier = Modifier.fillMaxSize()) {
                Image(imageVector = Icons.Default.Search, contentDescription = "Search", modifier = Modifier.padding(vertical = 25.dp).size(35.dp))
                //Text(text = "Search Comics...", style = TextStyle(color = Color.White))
            }
        }
    }
}