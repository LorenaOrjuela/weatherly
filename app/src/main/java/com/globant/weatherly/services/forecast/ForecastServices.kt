package com.globant.weatherly.services.forecast

import com.globant.weatherly.models.ForecastResponse
import com.globant.weatherly.models.WeatherResponse
import retrofit2.http.GET

interface ForecastServices {

    @GET("data/2.5/weather")
    suspend fun getWeather(): WeatherResponse

    @GET("data/2.5/forecast")
    suspend fun getForecast(): ForecastResponse
}