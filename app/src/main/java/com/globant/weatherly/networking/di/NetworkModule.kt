package com.globant.weatherly.networking.di

import android.content.Context
import com.globant.weatherly.networking.interceptors.ApiKeyInterceptor
import com.globant.weatherly.networking.interceptors.HeadersInterceptor
import com.globant.weatherly.networking.interceptors.QueryInterceptor
import com.globant.weatherly.services.WeatherServices
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
    fun retrofit(gson: Gson, appContext: Context): Retrofit {
        return Retrofit.Builder()
            .client(httpClient(appContext))
            //TODO: An uls file was created, please use to store this url http://api.openweathermap.org, is better to implements flavors.
            .baseUrl("http://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun forecast(retrofit: Retrofit): WeatherServices {
        return retrofit.create(WeatherServices::class.java)
    }

    private fun httpClient(appContext: Context): OkHttpClient {
        val interceptors = getHttpInterceptors(appContext)
        val builder = OkHttpClient.Builder()

        interceptors.forEach { builder.addInterceptor(it) }

        return builder.build()
    }

    private fun getHttpInterceptors(appContext: Context): List<Interceptor> {

        return listOfNotNull(
            ApiKeyInterceptor(),
            HeadersInterceptor(),
            QueryInterceptor(appContext)
        )
    }
}