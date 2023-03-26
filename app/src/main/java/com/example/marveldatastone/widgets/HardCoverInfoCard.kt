package com.example.marveldatastone.widgets


import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.example.marveldatastone.model.CharacterModels.HardCover.Result
import com.example.marveldatastone.screens.SharedViewModel.SharedViewModel


@Composable
fun HardCoverInfoCard(sharedViewModel: SharedViewModel){

    val list=sharedViewModel.hardCoverResult

    var slist by remember {
        mutableStateOf<Result?>(null)
    }

    LaunchedEffect(key1 = list)
    {
        if(list!=null)
        {
            slist=list
        }
    }

    if(slist!=null)
    Text(text = "Title : ${slist!!.title}")
}