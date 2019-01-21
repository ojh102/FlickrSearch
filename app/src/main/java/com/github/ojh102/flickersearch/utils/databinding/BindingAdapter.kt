package com.github.ojh102.flickersearch.utils.databinding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

internal class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("android:visibility")
        fun setVisibility(view: View, isVisible: Boolean) {
            view.visibility = if(isVisible) View.VISIBLE else View.GONE
        }

        @JvmStatic
        @BindingAdapter("srcCompat")
        fun setSrcCompat(imageView: ImageView, url: String) {
            Glide.with(imageView)
                    .load(url)
                    .into(imageView)
        }

        @JvmStatic
        @BindingAdapter("android:selected")
        fun setSelected(view: View, selected: Boolean) {
            view.isSelected = selected
        }
    }
}