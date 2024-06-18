package com.globant.weatherly.networking.interceptors

import com.globant.weatherly.networking.HttpConstants
import com.globant.weatherly.networking.HttpConstants.LAT
import com.globant.weatherly.networking.HttpConstants.LON
import com.globant.weatherly.networking.HttpConstants.UNITS
import com.globant.weatherly.networking.HttpConstants.IMPERIAL
import okhttp3.Interceptor
import okhttp3.Response

class QueryInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val lat = "4.67"
        val lon = "-74.08"
        val request = chain.request()
        val url = request.url
        val urlBuilder = url.newBuilder()

        val refreshUrl = urlBuilder
            .addQueryParameter(LAT, lat)
            .addQueryParameter(LON, lon)
            .addQueryParameter(UNITS, IMPERIAL)
            .build()

        val refreshRequest = request.newBuilder()
            .url(refreshUrl)
            .build()

        return chain.proceed(refreshRequest)
    }
}