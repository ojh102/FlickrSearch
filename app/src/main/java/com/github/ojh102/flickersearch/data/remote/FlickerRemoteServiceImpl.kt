package com.github.ojh102.flickersearch.data.remote

import com.github.ojh102.flickersearch.data.remote.api.FlickerApi
import javax.inject.Inject

internal class FlickerRemoteServiceImpl @Inject constructor(
    private val flickerApi: FlickerApi

) : FlickerRemoteService {

}