package com.github.ojh102.flickrsearch.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import com.github.ojh102.flickrsearch.base.ViewModelProviderFactory
import com.github.ojh102.flickrsearch.utils.rx.AppSchedulerProvider
import com.github.ojh102.flickrsearch.utils.rx.SchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationModule.ProvideModule::class])
internal interface ApplicationModule {
    @Module
    class ProvideModule {
        @Provides
        @Singleton
        fun provideResources(context: Context): Resources {
            return context.resources
        }
    }

    @Binds
    @Singleton
    fun bindContext(application: Application): Context

    @Binds
    @Singleton
    fun bindSchedulerProvider(appSchedulerProvider: AppSchedulerProvider): SchedulerProvider

    @Binds
    fun bindViewModelFactory(providerFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}