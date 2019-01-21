package com.github.ojh102.flickrsearch.utils

import com.github.ojh102.flickrsearch.data.remote.response.FlickrPhotoResponse

internal class FlickrUrlParser {
    companion object {
        fun parse(flickrPhotoResponse: FlickrPhotoResponse): String {
            return "https://farm${flickrPhotoResponse.farm}.staticflickr.com/${flickrPhotoResponse.server}/${flickrPhotoResponse.id}_${flickrPhotoResponse.secret}.jpg"
        }
    }
}