package com.globant.weatherly.services

import android.content.Context
import com.globant.weatherly.models.ForecastDay
import com.globant.weatherly.models.ForecastResponse
import com.globant.weatherly.models.WeatherResponse

interface IWeatherController {

    suspend fun getWeather(lat: String, lon: String): WeatherResponse?

    suspend fun getForecast(lat: String, lon: String): ForecastResponse?

    suspend fun getTodayForecast(lat: String, lon: String): List<WeatherResponse>?

    suspend fun getFiveDaysForecast(context: Context, lat: String, lon: String): List<ForecastDay>?
}