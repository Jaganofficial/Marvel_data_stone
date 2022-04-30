package com.example.marveldatastone.model.CharacterModels.TradePaperBackModel

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)