package com.github.ojh102.flickrsearch.utils

import com.github.ojh102.flickrsearch.data.remote.response.FlickrPhoto

internal class FlickrUrlParser {
    companion object {
        fun parse(flickrPhoto: FlickrPhoto): String {
            return "https://farm${flickrPhoto.farm}.staticflickr.com/${flickrPhoto.server}/${flickrPhoto.id}_${flickrPhoto.secret}.jpg"
        }
    }
}