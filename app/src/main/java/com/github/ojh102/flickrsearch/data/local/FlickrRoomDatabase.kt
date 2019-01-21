package com.github.ojh102.flickrsearch.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.ojh102.flickrsearch.data.local.dao.PhotoDao
import com.github.ojh102.flickrsearch.data.local.entity.PhotoEntity

@Database(
    entities = [
        PhotoEntity::class
    ],
    version = 1,
    exportSchema = false
)
internal abstract class FlickrRoomDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}