package com.globant.weatherly.networking.interceptors

import android.content.Context
import com.globant.weatherly.networking.HttpConstants.LAT
import com.globant.weatherly.networking.HttpConstants.LON
import com.globant.weatherly.networking.HttpConstants.UNITS
import com.globant.weatherly.networking.HttpConstants.IMPERIAL
import com.globant.weatherly.utils.LocationUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class QueryInterceptor @Inject constructor(@ApplicationContext private val appContext: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = runBlocking {

        val request = chain.request()
        val url = request.url
        val urlBuilder = url.newBuilder()

        val location = LocationUtils.getLocation(appContext)
        val lat = location.latitude.toString()
        val lon = location.longitude.toString()

        val refreshUrl = urlBuilder
            .addQueryParameter(LAT, lat)
            .addQueryParameter(LON, lon)
            .addQueryParameter(UNITS, IMPERIAL)
            .build()

        val refreshRequest = request.newBuilder()
            .url(refreshUrl)
            .build()

        chain.proceed(refreshRequest)
    }
}