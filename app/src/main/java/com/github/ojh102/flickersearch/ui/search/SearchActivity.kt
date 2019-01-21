package com.github.ojh102.flickersearch.ui.search

import android.os.Bundle
import com.github.ojh102.flickersearch.R
import com.github.ojh102.flickersearch.base.BaseActivity
import com.github.ojh102.flickersearch.databinding.ActivitySearchBinding

internal class SearchActivity : BaseActivity<ActivitySearchBinding, SearchActivityViewModel>() {

    override val layoutRes: Int = R.layout.activity_search

    override val viewModelClass: Class<SearchActivityViewModel> = SearchActivityViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}
