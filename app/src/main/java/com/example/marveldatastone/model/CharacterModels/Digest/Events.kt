package com.example.marveldatastone.model.CharacterModels.Digest

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)