package com.github.ojh102.flickersearch.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo")
internal data class PhotoEntity(
    @PrimaryKey
    val url: String
)