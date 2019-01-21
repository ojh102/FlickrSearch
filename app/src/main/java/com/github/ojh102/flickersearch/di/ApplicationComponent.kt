package com.github.ojh102.flickersearch.di

import android.app.Application
import com.github.ojh102.flickersearch.FlickerApplication
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
internal interface ApplicationComponent : AndroidInjector<FlickerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}