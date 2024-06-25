package com.globant.weatherly.services

import com.globant.weatherly.models.ForecastDay
import com.globant.weatherly.models.ForecastResponse
import com.globant.weatherly.models.WeatherResponse

interface IWeatherController {

    suspend fun getWeather(): WeatherResponse?

    suspend fun getForecast(): ForecastResponse?

    suspend fun getTodayForecast(): List<WeatherResponse>?

    suspend fun getFiveDaysForecast(): List<ForecastDay>?
}