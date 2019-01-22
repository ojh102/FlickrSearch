package com.github.ojh102.flickrsearch.ui.search

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.ojh102.flickrsearch.R
import com.github.ojh102.flickrsearch.base.BaseActivity
import com.github.ojh102.flickrsearch.databinding.ActivitySearchBinding
import com.github.ojh102.flickrsearch.ui.search.keyword.KeywordAdapter
import com.github.ojh102.flickrsearch.ui.search.keyword.KeywordItem
import com.github.ojh102.flickrsearch.ui.search.photo.PhotoAdapter
import com.github.ojh102.flickrsearch.utils.extension.observe
import javax.inject.Inject

internal class SearchActivity : BaseActivity<ActivitySearchBinding, SearchActivityViewModel>() {

    @Inject
    lateinit var keywordAdapter: KeywordAdapter

    @Inject
    lateinit var photoAdapter: PhotoAdapter

    override val layoutRes: Int = R.layout.activity_search

    override val viewModelClass: Class<SearchActivityViewModel> = SearchActivityViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeRecyclerView()

        observe(viewModel.keywordList) { keywordAdapter.submitList(it) }
        observe(viewModel.photoList) { photoAdapter.submitList(it) }
    }

    private fun initializeRecyclerView() {
        binding.rvKeyword.run {
            layoutManager = LinearLayoutManager(this@SearchActivity, RecyclerView.HORIZONTAL, false)
            adapter = keywordAdapter
        }

        binding.rvPhoto.run {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = photoAdapter
        }

        keywordAdapter.setCallbacks(object : KeywordAdapter.Callbacks {
            override fun onClickKeyword(item: KeywordItem) {
                viewModel.toAction(SearchAction.Click.Keyword(item))
            }
        })
    }

}
