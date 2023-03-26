package com.example.marveldatastone.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.marveldatastone.model.CharacterModels.BottomNavigationBarModel.BottomNavItem

@Composable
fun BottomNavigationBar(items:List<BottomNavItem>,navController: NavController,onItemClick: (BottomNavItem)->Unit) {
    val backStackEntry=navController.currentBackStackEntryAsState()
    BottomNavigation(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp).clip(RoundedCornerShape(15.dp)), backgroundColor = Color.Black, elevation = 10.dp) {
        items.forEach{
            item->
            val selected=item.route==backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.LightGray
                ,icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                     Icon(imageVector = item.image, contentDescription = item.name)
                        if(selected)
                            Text(text = item.name, fontSize = 10.sp, textAlign = TextAlign.Center)
                    }
            })
        }
    }
}