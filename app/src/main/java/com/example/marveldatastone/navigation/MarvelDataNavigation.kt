package com.example.marveldatastone.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.example.marveldatastone.screens.SearchScreen.SearchScreen
import com.example.marveldatastone.screens.SearchScreen.SearchScreenViewModel
import com.example.marveldatastone.screens.SharedViewModel.BooksInfoScreen
import com.example.marveldatastone.screens.SharedViewModel.SharedViewModel
import com.example.marveldatastone.screens.ShowAll.ShowAllComics.GraphicNovel.ShowAllGraphicNovel
import com.example.marveldatastone.screens.ShowAll.ShowAllComics.GraphicNovel.ShowAllGraphicNovelViewModel
import com.example.marveldatastone.screens.ShowAll.ShowAllComics.InfiniteNovelShowAll.ShowAllInfiniteNovel
import com.example.marveldatastone.screens.ShowAll.ShowAllComics.InfiniteNovelShowAll.ShowAllInfiniteNovelViewModel
import com.example.marveldatastone.screens.ShowAll.ShowAllComics.ShowAllComicsViewModel
import com.example.marveldatastone.screens.ShowAll.ShowAllComics.ShowAllDigest.ShowAllDigest
import com.example.marveldatastone.screens.ShowAll.ShowAllComics.ShowAllDigest.ShowAllDigestViewModel
import com.example.marveldatastone.screens.ShowAll.ShowAllComics.ShowAllHardCover.ShowAllHardCover
import com.example.marveldatastone.screens.ShowAll.ShowAllComics.ShowAllHardCover.ShowAllHardCoverViewModel
import com.example.marveldatastone.screens.ShowAll.ShowAllComics.ShowAlltradePaperback.ShowAllTradePaperBack
import com.example.marveldatastone.screens.ShowAll.ShowAllComics.ShowAlltradePaperback.ShowAllTradePaperBackViewModel
import com.example.marveldatastone.widgets.HardCoverInfoCard
import com.example.marveldatastone.widgets.ShowAllComics

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MarvelDataNavigation(navController: NavHostController) {


    //Shared View Model
    val sharedViewModel: SharedViewModel= viewModel()

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
            MarvelMainScreen(navController = navController,mainViewModel,sharedViewModel)
        }

        //BooksScreen
        composable(MarvelDataScreens.BooksScreen.name)
        {
            val booksViewModel= hiltViewModel<BooksViewModel>()
            BooksScreen(navController = navController,booksViewModel,sharedViewModel)
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
            HardCoverInfoCard(sharedViewModel)
        }

        composable(MarvelDataScreens.ShowAllComics.name)
        {
            val showAllComicsViewModel= hiltViewModel<ShowAllComicsViewModel>()
            ShowAllComics(showAllComicsViewModel,sharedViewModel, navController = navController)
        }

        composable(MarvelDataScreens.BooksInfoScreen.name)
        {
            BooksInfoScreen(sharedViewModel = sharedViewModel)
        }

        composable(MarvelDataScreens.ShowAllInfiniteComics.name)
        {
            val showAllInfiniteNovelViewModel= hiltViewModel<ShowAllInfiniteNovelViewModel>()
            ShowAllInfiniteNovel(showAllInfiniteNovelViewModel,sharedViewModel, navController = navController)
        }

        composable(MarvelDataScreens.ShowAllHardCover.name)
        {
            val showAllHardCoverViewModel= hiltViewModel<ShowAllHardCoverViewModel>()
            ShowAllHardCover(showAllHardCoverViewModel,sharedViewModel, navController = navController)
        }

        composable(MarvelDataScreens.ShowAllTradePaperBook.name)
        {
            val showAllTradePaperBackViewModel= hiltViewModel<ShowAllTradePaperBackViewModel>()
            ShowAllTradePaperBack(showAllTradePaperBackViewModel,sharedViewModel, navController = navController)
        }

        composable(MarvelDataScreens.ShowAllDigest.name)
        {
            val showAllDigestViewModel= hiltViewModel<ShowAllDigestViewModel>()
            ShowAllDigest(showAllDigestViewModel,sharedViewModel, navController = navController)
        }

        composable(MarvelDataScreens.ShowAllGraphicNovel.name)
        {
            val showAllGraphicNovelViewModel= hiltViewModel<ShowAllGraphicNovelViewModel>()
            ShowAllGraphicNovel(showAllGraphicNovelViewModel,sharedViewModel, navController = navController)
        }

        composable(MarvelDataScreens.SearchScreen.name)
        {
            val searchAllComicsViewModel= hiltViewModel<SearchScreenViewModel>()
            SearchScreen(searchAllComicsViewModel,sharedViewModel, navController = navController)
        }

    }
}