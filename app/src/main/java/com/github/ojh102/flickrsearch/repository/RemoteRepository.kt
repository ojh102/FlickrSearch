package com.github.ojh102.flickrsearch.repository

import androidx.paging.PagedList
import com.github.ojh102.flickrsearch.data.remote.response.FlickrPhoto
import com.github.ojh102.flickrsearch.data.remote.response.FlickrSearchResponse
import io.reactivex.Observable
import io.reactivex.Single

internal interface RemoteRepository {
    fun search(keyword: String, page: Int): Single<FlickrSearchResponse>

    fun search(keyword: String): Observable<PagedList<FlickrPhoto>>
}