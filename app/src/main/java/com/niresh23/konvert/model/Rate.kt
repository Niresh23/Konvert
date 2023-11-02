package com.niresh23.konvert.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Rate(
    @PrimaryKey val code: String = "",
    val value: Float = 0f,
) {
    @Ignore
    var isFavorite: Boolean = false
}
