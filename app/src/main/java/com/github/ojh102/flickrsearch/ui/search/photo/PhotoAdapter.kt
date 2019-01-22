package com.github.ojh102.flickrsearch.ui.search.photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.github.ojh102.flickrsearch.R
import com.github.ojh102.flickrsearch.data.remote.response.FlickrPhoto
import com.github.ojh102.flickrsearch.databinding.ViewPhotoBinding


internal class PhotoAdapter : PagedListAdapter<FlickrPhoto, PhotoViewHolder>(object : DiffUtil.ItemCallback<FlickrPhoto>() {
        override fun areItemsTheSame(oldItem: FlickrPhoto, newItem: FlickrPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FlickrPhoto, newItem: FlickrPhoto): Boolean {
            return oldItem.id == newItem.id
        }

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<ViewPhotoBinding>(inflater, R.layout.view_photo, parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(
                PhotoItem(
                    title = it.title,
                    url = it.url
                )
            )
        }
    }

}