package com.github.ojh102.flickrsearch.ui.search.keyword

import androidx.recyclerview.widget.RecyclerView
import com.github.ojh102.flickrsearch.databinding.ViewKeywordBinding

internal class KeywordViewHolder(private val binding: ViewKeywordBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: KeywordItem) {
        binding.item = item
    }
}