package com.example.marveldatastone.model.CharacterModels.ComicsModels

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)