package com.github.ojh102.flickrsearch.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.github.ojh102.flickrsearch.data.local.entity.PhotoEntity
import io.reactivex.Flowable

@Dao
internal interface PhotoDao : BaseDao<PhotoEntity> {
    @Query("SELECT * FROM photoResponse")
    fun gets(): Flowable<PhotoEntity>
}