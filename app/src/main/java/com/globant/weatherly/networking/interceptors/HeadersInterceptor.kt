package com.globant.weatherly.networking.interceptors

import com.globant.weatherly.networking.HttpConstants.CONTENT_TYPE
import com.globant.weatherly.networking.HttpConstants.APPLICATION_JSON
import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder()

        builder
            .header(
                CONTENT_TYPE,
                APPLICATION_JSON
            )

        val freshRequest = builder.build()

        return chain.proceed(freshRequest)
    }
}