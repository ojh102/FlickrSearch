package com.github.ojh102.flickrsearch.utils

import android.content.res.Resources
import javax.inject.Inject

internal class ResourcesProviderImpl @Inject constructor(
    private val resources: Resources

) : ResourcesProvider {

    override fun getString(resId: Int): String {
        return resources.getString(resId)
    }

    override fun getStringArray(resId: Int): Array<String> {
        return resources.getStringArray(resId)
    }
}