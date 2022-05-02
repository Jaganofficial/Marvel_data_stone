package com.example.marveldatastone.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.marveldatastone.screens.BooksScreen.BooksScreen
import com.example.marveldatastone.screens.BooksScreen.BooksViewModel
import com.example.marveldatastone.screens.ComicsSearch
import com.example.marveldatastone.screens.FavoriteScreen.FavoriteScreen
import com.example.marveldatastone.screens.Main.MainViewModel
import com.example.marveldatastone.screens.MarvelMainScreen
import com.example.marveldatastone.screens.MarvelSplashScreen
import com.example.marveldatastone.screens.ShowAllComics.ShowAllComicsViewModel
import com.example.marveldatastone.widgets.HardCoverInfoCard
import com.example.marveldatastone.widgets.ShowAllComics

@Composable
fun MarvelDataNavigation(navController: NavHostController) {

    NavHost(navController = navController, 
            startDestination = MarvelDataScreens.SplashScreen.name ){
        //SplashScreen
        composable(MarvelDataScreens.SplashScreen.name)
        {
            MarvelSplashScreen(navController = navController)
        }

        //MainScreen
        composable(MarvelDataScreens.MainScreen.name)
        {
            val mainViewModel= hiltViewModel<MainViewModel>()
            MarvelMainScreen(navController = navController,mainViewModel)
        }

        //BooksScreen
        composable(MarvelDataScreens.BooksScreen.name)
        {
            val booksViewModel= hiltViewModel<BooksViewModel>()
            BooksScreen(navController = navController,booksViewModel)
        }

        //FavoriteScreen
        composable(MarvelDataScreens.FavoriteScreen.name)
        {
            FavoriteScreen(navController = navController)
        }

        //
        composable(MarvelDataScreens.ComicsSearchScreen.name)
        {
            ComicsSearch()
        }

        //HardCoverInfoScreen
        composable(MarvelDataScreens.HardCoverInfoScreen.name)
        {
            //HardCoverInfoCard()
        }

        composable(MarvelDataScreens.ShowAllComics.name)
        {
            val showAllComicsViewModel= hiltViewModel<ShowAllComicsViewModel>()
            ShowAllComics(showAllComicsViewModel)
        }
    }
}