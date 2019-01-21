package com.github.ojh102.flickrsearch.data

import androidx.paging.PageKeyedDataSource
import com.github.ojh102.flickrsearch.data.remote.FlickrRemoteService
import com.github.ojh102.flickrsearch.data.remote.response.FlickrPhoto
import com.github.ojh102.flickrsearch.utils.extension.subscribeOf
import io.reactivex.disposables.CompositeDisposable

internal class SearchDataSource(
        private val keyword: String,
        private val remoteService: FlickrRemoteService,
        private val disposables: CompositeDisposable

) : PageKeyedDataSource<Int, FlickrPhoto>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, FlickrPhoto>) {
        disposables.add(
                remoteService.search(keyword, 1)
                        .subscribeOf(onSuccess = { callback.onResult(it.photos.photo, 1, 2) })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, FlickrPhoto>) {
        disposables.add(
                remoteService.search(keyword, params.key)
                        .subscribeOf(onSuccess = { callback.onResult(it.photos.photo, params.key + 1) })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, FlickrPhoto>) {
        
    }

}

