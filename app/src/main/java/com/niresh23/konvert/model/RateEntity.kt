package com.niresh23.konvert.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rate_table")
class RateEntity(
    @PrimaryKey
    val code: String = "",
    val value: Float = 0f,
    val timestamp: Long,
    val name: String
)