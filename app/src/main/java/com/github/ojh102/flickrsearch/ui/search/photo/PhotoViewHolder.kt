package com.github.ojh102.flickrsearch.ui.search.photo

import androidx.recyclerview.widget.RecyclerView
import com.github.ojh102.flickrsearch.databinding.ViewPhotoBinding

internal class PhotoViewHolder(private val binding: ViewPhotoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PhotoItem) {
        binding.item = item
    }

}