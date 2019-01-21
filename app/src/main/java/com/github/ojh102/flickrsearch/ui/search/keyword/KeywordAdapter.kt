package com.github.ojh102.flickrsearch.ui.search.keyword

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.ojh102.flickrsearch.R
import com.github.ojh102.flickrsearch.databinding.ViewKeywordBinding

internal class KeywordAdapter : ListAdapter<KeywordItem, KeywordViewHolder>(object : DiffUtil.ItemCallback<KeywordItem>() {
    override fun areItemsTheSame(oldItem: KeywordItem, newItem: KeywordItem): Boolean {
        return oldItem.keyword == newItem.keyword
    }

    override fun areContentsTheSame(oldItem: KeywordItem, newItem: KeywordItem): Boolean {
        return oldItem.keyword == newItem.keyword
    }

}) {

    interface Callbacks {
        fun onClickKeyword(item: KeywordItem)
    }

    private var callbacks: Callbacks? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<ViewKeywordBinding>(inflater, R.layout.view_keyword, parent, false)

        val viewHolder = KeywordViewHolder(binding)

        binding.clickListener = View.OnClickListener {
            val adapterPosition = viewHolder.adapterPosition

            if(adapterPosition != RecyclerView.NO_POSITION) {
                callbacks?.onClickKeyword(getItem(adapterPosition))
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: KeywordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setCallbacks(callbacks: Callbacks) {
        this.callbacks = callbacks
    }

}