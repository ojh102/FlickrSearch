package com.github.ojh102.flickrsearch.repository

import com.github.ojh102.flickrsearch.data.remote.response.FlickrSearchResponse
import io.reactivex.Single

internal interface RemoteRepository {
    fun search(keyword: String, page: Int): Single<FlickrSearchResponse>
}