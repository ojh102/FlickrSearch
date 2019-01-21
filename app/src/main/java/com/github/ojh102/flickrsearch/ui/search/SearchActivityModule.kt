package com.github.ojh102.flickrsearch.ui.search

import androidx.lifecycle.ViewModel
import com.github.ojh102.flickrsearch.di.key.ViewModelKey
import com.github.ojh102.flickrsearch.ui.search.keyword.KeywordAdapter
import com.github.ojh102.flickrsearch.ui.search.photo.PhotoAdapter
import com.github.ojh102.flickrsearch.di.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [SearchActivityModule.ProvideModule::class])
internal interface SearchActivityModule {
    @Module
    class ProvideModule {
        @Provides
        fun provideKeywordAdapter(): KeywordAdapter {
            return KeywordAdapter()
        }

        @Provides
        fun providePhotoAdapter(): PhotoAdapter {
            return PhotoAdapter()
        }
    }

    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(SearchActivityViewModel::class)
    fun bindSearchActivityViewModel(viewmodel: SearchActivityViewModel): ViewModel
}