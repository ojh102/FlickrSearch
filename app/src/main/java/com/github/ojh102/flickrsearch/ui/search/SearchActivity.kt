package com.github.ojh102.flickrsearch.ui.search

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.ojh102.flickrsearch.R
import com.github.ojh102.flickrsearch.base.BaseActivity
import com.github.ojh102.flickrsearch.databinding.ActivitySearchBinding

internal class SearchActivity : BaseActivity<ActivitySearchBinding, SearchActivityViewModel>() {

    override val layoutRes: Int = R.layout.activity_search

    override val viewModelClass: Class<SearchActivityViewModel> = SearchActivityViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        binding.rvKeyword.run {
            layoutManager = LinearLayoutManager(this@SearchActivity, RecyclerView.HORIZONTAL, false)
        }

        binding.rvPhoto.run {
            layoutManager = LinearLayoutManager(this@SearchActivity)
        }
    }

}
