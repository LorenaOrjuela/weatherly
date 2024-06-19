package com.globant.weatherly.services.implementation

import android.util.Log
import com.globant.weatherly.models.ForecastResponse
import com.globant.weatherly.models.Weather
import com.globant.weatherly.models.WeatherResponse
import com.globant.weatherly.services.IWeatherController
import com.globant.weatherly.utils.DATE_TIME
import com.globant.weatherly.utils.getDate
import com.globant.weatherly.utils.getDateTime
import com.globant.weatherly.utils.now
import javax.inject.Inject

class WeatherController @Inject constructor(
    private val weatherRepository : WeatherRepository
): IWeatherController {

    override suspend fun getWeather(): WeatherResponse? {
        return weatherRepository.getWeather()
    }

    override suspend fun getForecast(): ForecastResponse? {
        return weatherRepository.getForecast()
    }

    override suspend fun getTodayForecast(): List<WeatherResponse>? {
        val now = getDateTime(now(), DATE_TIME)
        val getDateToday = getDate(now,DATE_TIME)
        val response = getForecast()

        return response?.forecasts?.filter { weather ->
            weather.date?.let { date ->
                getDate(date, DATE_TIME)
                //} == getDateToday
            } == "2024-06-20"
        }
    }
}