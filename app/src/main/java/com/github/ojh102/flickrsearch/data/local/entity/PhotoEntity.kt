package com.github.ojh102.flickrsearch.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photoResponse")
internal data class PhotoEntity(
    @PrimaryKey
    val url: String
)