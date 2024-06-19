package com.globant.weatherly.services

import com.globant.weatherly.models.ForecastResponse
import com.globant.weatherly.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET

interface WeatherServices {

    @GET("data/2.5/weather")
    suspend fun getWeather(): Response<WeatherResponse>

    @GET("data/2.5/forecast")
    suspend fun getForecast(): Response<ForecastResponse>
}