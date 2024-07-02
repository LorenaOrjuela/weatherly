package com.globant.weatherly.networking.interceptors

import com.globant.weatherly.networking.HttpConstants.APY_KEY
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        //TODO store it suing DataStore Preferences
        val apiKey = "caad787b9089c6114d7a0ac1e50a8b10"
        val request = chain.request()
        val url = request.url
        val urlBuilder = url.newBuilder()

        val refreshUrl = urlBuilder
            .addQueryParameter(APY_KEY, apiKey)
            .build()

        val freshRequest = request.newBuilder()
            .url(refreshUrl)
            .build()

        return chain.proceed(freshRequest)
    }
}