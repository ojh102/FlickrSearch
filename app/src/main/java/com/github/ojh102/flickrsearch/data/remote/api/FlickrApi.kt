package com.github.ojh102.flickrsearch.data.remote.api

import com.github.ojh102.flickrsearch.data.remote.response.FlickrSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface FlickrApi {
    @GET("rest")
    fun search(
        @Query("tags") keyword: String,
        @Query("page") page: Int,

        @Query("per_page") perPage: Int = 20,
        @Query("method") method: String = "flickr.photos.search",
        @Query("api_key") apiKey: String = "2f904f1669187c7860cae324d891ccd3",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallBack: Int = 1

    ): Single<FlickrSearchResponse>
}