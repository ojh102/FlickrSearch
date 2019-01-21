package com.github.ojh102.flickrsearch.di

import com.github.ojh102.flickrsearch.ui.search.SearchActivity
import com.github.ojh102.flickrsearch.ui.search.SearchActivityModule
import com.plug.android.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal interface ActivityModule {
    @ContributesAndroidInjector(modules = [SearchActivityModule::class])
    @ActivityScope
    fun bindSearchActivity(): SearchActivity
}