package com.github.ojh102.flickrsearch.utils

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

internal interface ResourcesProvider {
    fun getString(@StringRes resId: Int): String
    fun getStringArray(@ArrayRes resId: Int): Array<String>
}