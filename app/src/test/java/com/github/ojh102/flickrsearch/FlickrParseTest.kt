package com.github.ojh102.flickrsearch

import com.github.ojh102.flickrsearch.data.remote.response.FlickrPhoto
import com.github.ojh102.flickrsearch.utils.FlickrUrlParser
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class FlickrParseTest {

    lateinit var flickrPhoto: FlickrPhoto

    @Before
    fun setup() {
        flickrPhoto = FlickrPhoto(
                id = "id",
                owner = "owner",
                secret = "secret",
                server = "server",
                farm = 0,
                title = "title"
        )
    }

    @Test
    fun parse_isCorrect() {
        assertEquals("https://farm0.staticflickr.com/server/id_secret.jpg", FlickrUrlParser.parse(flickrPhoto))
    }
}
