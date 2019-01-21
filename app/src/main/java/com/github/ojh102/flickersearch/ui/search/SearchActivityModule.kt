package com.github.ojh102.flickersearch.ui.search

import androidx.lifecycle.ViewModel
import com.github.ojh102.flickersearch.di.key.ViewModelKey
import com.plug.android.di.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface SearchActivityModule {
    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(SearchActivityViewModel::class)
    fun bindSearchActivityViewModel(viewmodel: SearchActivityViewModel): ViewModel
}