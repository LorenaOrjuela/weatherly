package com.globant.weatherly.networking.interceptors

import com.globant.weatherly.networking.HttpConstants.UNITS
import com.globant.weatherly.networking.HttpConstants.IMPERIAL
import okhttp3.Interceptor
import okhttp3.Response

class QueryInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val url = request.url
        val urlBuilder = url.newBuilder()

        val refreshUrl = urlBuilder
            .addQueryParameter(UNITS, IMPERIAL)
            .build()

        val refreshRequest = request.newBuilder()
            .url(refreshUrl)
            .build()

        return chain.proceed(refreshRequest)
    }
}