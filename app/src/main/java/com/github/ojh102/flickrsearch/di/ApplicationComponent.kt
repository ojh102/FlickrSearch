package com.github.ojh102.flickrsearch.di

import android.app.Application
import com.github.ojh102.flickrsearch.FlickrApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    NetworkModule::class,
    DatabaseModule::class,
    RepositoryModule::class,
    ApplicationModule::class
])
internal interface ApplicationComponent : AndroidInjector<FlickrApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}