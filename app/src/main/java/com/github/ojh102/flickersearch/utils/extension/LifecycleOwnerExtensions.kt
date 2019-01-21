package com.github.ojh102.flickersearch.utils.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

internal fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, block: (T) -> Unit) = liveData.observe(this, Observer {
    block(it)
})