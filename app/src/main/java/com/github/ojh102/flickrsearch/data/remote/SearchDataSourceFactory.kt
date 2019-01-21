package com.github.ojh102.flickrsearch.data.remote

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.github.ojh102.flickrsearch.data.SearchDataSource
import com.github.ojh102.flickrsearch.data.remote.response.FlickrPhoto
import io.reactivex.disposables.CompositeDisposable


internal class SearchDataSourceFactory(
        private val keyword: String,
        private val remoteService: FlickrRemoteService,
        private val disposables: CompositeDisposable
) : DataSource.Factory<Int, FlickrPhoto>() {

    private val mutableDataSource = MutableLiveData<SearchDataSource>()

    override fun create(): DataSource<Int, FlickrPhoto> {
        val dataSource = SearchDataSource(keyword ,remoteService, disposables)
        mutableDataSource.postValue(dataSource)
        return dataSource
    }

}