package com.github.ojh102.flickersearch.di

import com.github.ojh102.flickersearch.data.remote.FlickerRemoteService
import com.github.ojh102.flickersearch.data.remote.FlickerRemoteServiceImpl
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
    fun bindFlickerRemoteService(flickerRemoteServiceImpl: FlickerRemoteServiceImpl): FlickerRemoteService
}