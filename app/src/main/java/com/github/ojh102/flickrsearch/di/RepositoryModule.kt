package com.github.ojh102.flickrsearch.di

import com.github.ojh102.flickrsearch.repository.LocalRepository
import com.github.ojh102.flickrsearch.repository.LocalRepositoryImpl
import com.github.ojh102.flickrsearch.repository.RemoteRepository
import com.github.ojh102.flickrsearch.repository.RemoteRepositoryImpl
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