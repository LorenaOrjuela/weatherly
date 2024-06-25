package com.globant.weatherly.services

import com.globant.weatherly.models.ForecastResponse
import com.globant.weatherly.models.WeatherResponse

interface IWeatherRepository {

    suspend fun getWeather(): WeatherResponse?

    suspend fun getForecast(): ForecastResponse?
}