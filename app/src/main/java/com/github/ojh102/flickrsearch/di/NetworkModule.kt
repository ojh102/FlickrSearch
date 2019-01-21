package com.github.ojh102.flickrsearch.di

import com.github.ojh102.flickrsearch.data.remote.FlickrRemoteService
import com.github.ojh102.flickrsearch.data.remote.FlickrRemoteServiceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(
    includes = [
        HttpModule::class,
        RetrofitModule::class
    ]
)
internal interface NetworkModule {
    @Binds
    @Singleton
    fun bindFlickrRemoteService(flickrRemoteServiceImpl: FlickrRemoteServiceImpl): FlickrRemoteService
}