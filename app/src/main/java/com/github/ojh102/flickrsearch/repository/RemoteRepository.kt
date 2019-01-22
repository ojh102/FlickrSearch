package com.github.ojh102.flickrsearch.repository

import androidx.paging.PagedList
import com.github.ojh102.flickrsearch.data.remote.response.FlickrPhoto
import io.reactivex.Observable

internal interface RemoteRepository {
    fun search(keyword: String): Observable<PagedList<FlickrPhoto>>
}