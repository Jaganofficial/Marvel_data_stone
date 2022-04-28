package com.example.marveldatastone.model.CharacterModels.CharacteresModel

import androidx.room.Entity

@Entity(tableName = "Marvel_Character_Data")
data class Result(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>
)