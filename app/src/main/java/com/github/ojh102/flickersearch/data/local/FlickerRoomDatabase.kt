package com.github.ojh102.flickersearch.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.ojh102.flickersearch.data.local.dao.PhotoDao
import com.github.ojh102.flickersearch.data.local.entity.PhotoEntity

@Database(
    entities = [
        PhotoEntity::class
    ],
    version = 1,
    exportSchema = false
)
internal abstract class FlickerRoomDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}