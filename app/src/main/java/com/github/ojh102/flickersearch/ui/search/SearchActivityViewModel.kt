package com.github.ojh102.flickersearch.ui.search

import androidx.lifecycle.LiveData
import com.github.ojh102.flickersearch.base.BaseViewModel
import com.github.ojh102.flickersearch.utils.extension.toLiveData
import com.jakewharton.rxrelay2.BehaviorRelay
import javax.inject.Inject

internal class SearchActivityViewModel @Inject constructor(): BaseViewModel() {

    private val selectedKeywordRelay = BehaviorRelay.createDefault("apple")

    val selectedKeyword: LiveData<String> = selectedKeywordRelay.toLiveData()


}