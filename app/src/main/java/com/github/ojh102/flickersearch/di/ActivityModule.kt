package com.github.ojh102.flickersearch.di

import com.github.ojh102.flickersearch.ui.search.SearchActivity
import com.github.ojh102.flickersearch.ui.search.SearchActivityModule
import com.plug.android.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal interface ActivityModule {
    @ContributesAndroidInjector(modules = [SearchActivityModule::class])
    @ActivityScope
    fun bindSearchActivity(): SearchActivity
}