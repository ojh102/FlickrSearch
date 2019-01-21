package com.github.ojh102.flickrsearch.repository

import com.github.ojh102.flickrsearch.data.remote.FlickrRemoteService
import com.github.ojh102.flickrsearch.data.remote.response.FlickrSearchResponse
import io.reactivex.Single
import javax.inject.Inject

internal class RemoteRepositoryImpl @Inject constructor(
    private val remoteService: FlickrRemoteService

) : RemoteRepository {

    override fun search(keyword: String, page: Int): Single<FlickrSearchResponse> {
        return remoteService.search(keyword, page)
    }

}