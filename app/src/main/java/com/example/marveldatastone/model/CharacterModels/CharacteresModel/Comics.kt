package com.example.marveldatastone.model.CharacterModels.CharacteresModel

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)