package com.github.ojh102.flickersearch.di

import com.github.ojh102.flickersearch.repository.LocalRepository
import com.github.ojh102.flickersearch.repository.LocalRepositoryImpl
import com.github.ojh102.flickersearch.repository.RemoteRepository
import com.github.ojh102.flickersearch.repository.RemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface RepositoryModule {
    @Binds
    @Singleton
    fun bindRemoteRepository(remoteRepositoryImpl: RemoteRepositoryImpl): RemoteRepository

    @Binds
    @Singleton
    fun bindLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository
}