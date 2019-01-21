package com.github.ojh102.flickersearch.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.github.ojh102.flickersearch.data.local.entity.PhotoEntity
import io.reactivex.Flowable

@Dao
internal interface PhotoDao : BaseDao<PhotoEntity> {
    @Query("SELECT * FROM photo")
    fun gets(): Flowable<PhotoEntity>
}