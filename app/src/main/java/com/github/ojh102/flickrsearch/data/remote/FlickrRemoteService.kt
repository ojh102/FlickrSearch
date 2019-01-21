package com.github.ojh102.flickrsearch.data.remote

import com.github.ojh102.flickrsearch.data.remote.response.FlickrSearchResponse
import io.reactivex.Single

internal interface FlickrRemoteService {
    fun search(keyword: String, page: Int): Single<FlickrSearchResponse>
}