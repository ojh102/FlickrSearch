package com.github.ojh102.flickrsearch.ui.search

import androidx.paging.PagedList
import com.github.ojh102.flickrsearch.data.remote.response.FlickrPhoto
import com.github.ojh102.flickrsearch.ui.search.keyword.KeywordItem

internal sealed class SearchState {
    data class KeywordList(val keywordList: List<KeywordItem>) : SearchState()

    data class SelectedKeyword(val keyword: KeywordItem) : SearchState()

    data class SearchedPhotoList(val photoPagedList: PagedList<FlickrPhoto>) : SearchState()
}