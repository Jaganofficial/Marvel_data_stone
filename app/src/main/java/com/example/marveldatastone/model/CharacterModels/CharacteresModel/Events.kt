package com.example.marveldatastone.model.CharacterModels.CharacteresModel

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)