package com.globant.weatherly.services

import com.globant.weatherly.models.ForecastResponse
import com.globant.weatherly.models.WeatherResponse

interface IWeatherRepository {

    suspend fun getWeather(lat: String,lon: String): WeatherResponse?

    suspend fun getForecast(lat: String,lon: String): ForecastResponse?
}