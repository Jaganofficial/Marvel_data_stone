package com.example.marveldatastone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.example.marveldatastone.model.CharacterModels.BottomNavigationBarModel.BottomNavItem
import com.example.marveldatastone.navigation.MarvelDataNavigation
import com.example.marveldatastone.navigation.MarvelDataScreens
import com.example.marveldatastone.ui.theme.MarvelDataStoneTheme
import com.example.marveldatastone.widgets.BottomNavigationBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelDataApp()
        }
    }

    @Composable
    fun MarvelDataApp() {
        MarvelDataStoneTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                val navController= rememberNavController()
                Scaffold(bottomBar = {
                    BottomNavigationBar(items =
                    listOf(
                        BottomNavItem(
                            "Comics",
                            MarvelDataScreens.MainScreen.name,
                            Icons.Default.Home
                        ),
                        BottomNavItem(
                            "Books",
                            MarvelDataScreens.BooksScreen.name,
                            Icons.Default.Book
                        ),
                        BottomNavItem(
                            "Favorite",
                            MarvelDataScreens.FavoriteScreen.name,
                            Icons.Default.Favorite
                        )
                    )
                        , navController =navController
                        , onItemClick ={
                            navController.navigate(it.route) {
                                launchSingleTop = true
                                popUpTo(MarvelDataScreens.MainScreen.name)
                            }
                        } )
                }){
                    MarvelDataNavigation(navController)
                }
            }
            }
        }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MarvelDataStoneTheme {
            MarvelDataApp()
        }
    }
}