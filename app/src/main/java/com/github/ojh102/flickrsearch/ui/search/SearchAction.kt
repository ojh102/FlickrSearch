package com.github.ojh102.flickrsearch.ui.search

import com.github.ojh102.flickrsearch.ui.search.keyword.KeywordItem

internal sealed class SearchAction {
    sealed class Select : SearchAction() {
        data class Keyword(val keywordItem: KeywordItem) : Select()
    }
}