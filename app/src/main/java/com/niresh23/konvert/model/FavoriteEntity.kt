package com.niresh23.konvert.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
class FavoriteEntity (
    @PrimaryKey
    val code: String
)

