package com.globant.weatherly.networking.di

import com.globant.weatherly.networking.interceptors.ApiKeyInterceptor
import com.globant.weatherly.networking.interceptors.HeadersInterceptor
import com.globant.weatherly.networking.interceptors.QueryInterceptor
import com.globant.weatherly.services.forecast.ForecastServices
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun gson(): Gson {
        return Gson()
    }

    @Provides
    fun retrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(httpClient())
            .baseUrl("http://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun forecast(retrofit: Retrofit): ForecastServices {
        return retrofit.create(ForecastServices::class.java)
    }

    private fun httpClient(): OkHttpClient {
        val interceptors = getHttpInterceptors()
        val builder = OkHttpClient.Builder()

        interceptors.forEach { builder.addInterceptor(it) }

        return builder.build()
    }

    private fun getHttpInterceptors(): List<Interceptor> {
        return listOfNotNull(
            ApiKeyInterceptor(),
            HeadersInterceptor(),
            QueryInterceptor()
        )
    }
}