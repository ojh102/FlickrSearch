package com.github.ojh102.flickersearch.data.remote.api

import com.github.ojh102.flickersearch.data.remote.response.PhotoResponse
import io.reactivex.Single
import retrofit2.http.GET

internal interface FlickerApi {
    @GET
    fun search(keyword: String): Single<PhotoResponse>
}