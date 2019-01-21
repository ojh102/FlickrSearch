package com.github.ojh102.flickrsearch.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import com.github.ojh102.flickrsearch.base.ViewModelProviderFactory
import com.github.ojh102.flickrsearch.utils.ResourcesProvider
import com.github.ojh102.flickrsearch.utils.ResourcesProviderImpl
import com.github.ojh102.flickrsearch.utils.rx.AppSchedulerProvider
import com.github.ojh102.flickrsearch.utils.rx.SchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
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

        @Provides
        @Singleton
        fun provideCompositeDisposable(): CompositeDisposable {
            return CompositeDisposable()
        }
    }

    @Binds
    @Singleton
    fun bindContext(application: Application): Context

    @Binds
    @Singleton
    fun provideResoucreProvier(resourcesProviderImpl: ResourcesProviderImpl): ResourcesProvider

    @Binds
    @Singleton
    fun bindSchedulerProvider(appSchedulerProvider: AppSchedulerProvider): SchedulerProvider

    @Binds
    fun bindViewModelFactory(providerFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}