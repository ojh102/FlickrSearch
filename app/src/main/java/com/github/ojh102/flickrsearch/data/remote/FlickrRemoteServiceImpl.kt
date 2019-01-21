package com.github.ojh102.flickrsearch.data.remote

import com.github.ojh102.flickrsearch.data.remote.api.FlickrApi
import com.github.ojh102.flickrsearch.data.remote.response.FlickrSearchResponse
import com.plug.android.utils.rx.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

internal class FlickrRemoteServiceImpl @Inject constructor(
    private val flickrApi: FlickrApi,
    private val schedulerProvider: SchedulerProvider

) : FlickrRemoteService {

    override fun search(keyword: String, page: Int): Single<FlickrSearchResponse> {
        return flickrApi.search(keyword, page).subscribeOn(schedulerProvider.io())
    }
}