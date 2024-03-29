package com.example.marveldatastone.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun SearchBar(modifier: Modifier=Modifier) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(90.dp)
        .padding(15.dp)
        .background(Color.Transparent), shape = RoundedCornerShape(35.dp), elevation = 15.dp, contentColor = Color.White) {
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(10.dp))
                Image(imageVector = Icons.Default.Search, contentDescription = "Search", modifier = Modifier
                    .size(45.dp)
                    )
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "Search Comics...", style = TextStyle(color = Color.Gray, fontSize = 21.sp))
            }
    }
}