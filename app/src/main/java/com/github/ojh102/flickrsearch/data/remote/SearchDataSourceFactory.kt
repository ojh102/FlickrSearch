package com.github.ojh102.flickrsearch.data.remote

import androidx.paging.DataSource
import com.github.ojh102.flickrsearch.data.SearchDataSource
import com.github.ojh102.flickrsearch.data.remote.response.FlickrPhoto
import io.reactivex.disposables.CompositeDisposable


internal class SearchDataSourceFactory(
        private val keyword: String,
        private val remoteService: FlickrRemoteService,
        private val disposables: CompositeDisposable
) : DataSource.Factory<Int, FlickrPhoto>() {

    override fun create(): DataSource<Int, FlickrPhoto> {
        return SearchDataSource(keyword, remoteService, disposables)
    }

}