package com.github.ojh102.flickrsearch.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.github.ojh102.flickrsearch.data.remote.FlickrRemoteService
import com.github.ojh102.flickrsearch.data.remote.SearchDataSourceFactory
import com.github.ojh102.flickrsearch.data.remote.response.FlickrPhoto
import com.github.ojh102.flickrsearch.data.remote.response.FlickrSearchResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

internal class RemoteRepositoryImpl @Inject constructor(
    private val remoteService: FlickrRemoteService,
    private val disposable: CompositeDisposable

) : RemoteRepository {

    override fun search(keyword: String, page: Int): Single<FlickrSearchResponse> {
        return remoteService.search(keyword, page)
    }

    override fun search(keyword: String): Observable<PagedList<FlickrPhoto>> {
        val dataSourceFactory = SearchDataSourceFactory(keyword, remoteService, disposable)

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(40)
            .setEnablePlaceholders(false)
            .build()

        return RxPagedListBuilder(dataSourceFactory, config).buildObservable()
    }
}