package com.example.marveldatastone.model.CharacterModels.InfiniteNovel

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)