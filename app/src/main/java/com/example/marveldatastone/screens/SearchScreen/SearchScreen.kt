package com.example.marveldatastone.screens.SearchScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.marveldatastone.model.CharacterModels.ComicsModels.Result
import com.example.marveldatastone.navigation.MarvelDataScreens
import com.example.marveldatastone.screens.SharedViewModel.SharedViewModel
import com.example.marveldatastone.screens.ShowAll.ShowAllComics.ShowAllComicsViewModel
import com.example.marveldatastone.widgets.ShowAllCard
import java.util.*


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SearchScreen(searchAllComicsViewModel: SearchScreenViewModel, sharedViewModel: SharedViewModel, navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

            var searchText = remember{
                mutableStateOf("")
            }
            //Search Bar
//           TextField(value = searchText.value, onValueChange = {
//
//           }, modifier = Modifier
//               .fillMaxWidth()
//               .height(145.dp)
//               .padding(25.dp), textStyle = TextStyle(color=Color.Black))
            Spacer(modifier = Modifier.height(10.dp))
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(horizontal = 24.dp), shape = RoundedCornerShape(25.dp), backgroundColor = Color.White, elevation = 15.dp) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(15.dp))
                    Image(imageVector = Icons.Default.Search, contentDescription ="Books Search Image", modifier = Modifier.size(40.dp))
                    Spacer(modifier = Modifier.width(5.dp))
                    TextField(value = searchText.value, textStyle = TextStyle(color = Color.Black, fontSize = 16.sp), onValueChange = {
                        searchText.value=it
                    }, colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent, cursorColor = Color.Black, focusedIndicatorColor = Color.Black, unfocusedIndicatorColor = Color.Gray
                    ), placeholder = {
                        Text(text = "Search", style = TextStyle(Color.Gray, fontSize = 16.sp))
                    })
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
            var list= emptyList<Result>()

            if(searchAllComicsViewModel.comicsListVMForSearch.collectAsState().value.isNotEmpty())
            {
                list=searchAllComicsViewModel.comicsListVMForSearch.collectAsState().value[0].data.results
            }
            Column() {
                LazyColumn()
                {
                    items(list.filter {
                        it.title.lowercase(Locale.getDefault()).contains(searchText.value.lowercase(Locale.getDefault()))
                    })
                    {
                        var url = "${it.thumbnail.path}.${it.thumbnail.extension}"
                        val painter = rememberAsyncImagePainter(model = url)
                        var title = it.title
                        var writter = "Marvel"
                        var price = "Free"
                        if (it.creators.items.isNotEmpty())
                            writter = it.creators.items[0].name
                        if (writter.length > 26)
                            writter = writter.substring(0, 25).toString() + "..."
                        if (it.prices.isNotEmpty() && "" + it.prices[0].price != "0.0")
                            price = "$" + it.prices[0].price
                        ShowAllCard(
                            painter = painter,
                            title = title,
                            writer = writter,
                            price = price, modifier = Modifier.clickable {
                                sharedViewModel.addComicsResult(it)
                                navController.navigate(MarvelDataScreens.BooksInfoScreen.name)
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(150.dp))
            }

        }

    }

}