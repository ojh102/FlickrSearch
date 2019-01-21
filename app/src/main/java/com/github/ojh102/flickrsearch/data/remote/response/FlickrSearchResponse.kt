package com.github.ojh102.flickrsearch.data.remote.response

import com.github.ojh102.flickrsearch.utils.FlickrUrlParser

internal data class FlickrSearchResponse(
    val photos: FlickrPhotosResponse,
    val stat: String
)

internal data class FlickrPhotosResponse(
    val page: Int,
    val perpage: Int,
    val photo: List<FlickrPhoto> = mutableListOf()
)

internal data class FlickrPhoto(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String
) {

    val url: String
        get() = FlickrUrlParser.parse(this)
}