package com.github.ojh102.flickrsearch.data.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

internal interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upserts(vararg entities: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(entity: T)

    @Delete
    fun delete(entity: T)

    @Delete
    fun deletes(vararg entities: T)
}