package com.globant.weatherly.services.implementation

import android.content.Context
import android.util.Log
import com.globant.weatherly.extensions.getAverageAngle
import com.globant.weatherly.models.ForecastDay
import com.globant.weatherly.models.ForecastResponse
import com.globant.weatherly.models.WeatherResponse
import com.globant.weatherly.services.IWeatherController
import com.globant.weatherly.utils.DATE_TIME
import com.globant.weatherly.utils.getDate
import com.globant.weatherly.utils.getDateLetters
import com.globant.weatherly.utils.getDateTime
import com.globant.weatherly.utils.now
import javax.inject.Inject

class WeatherController @Inject constructor(
    private val weatherRepository : WeatherRepository
): IWeatherController {

    override suspend fun getWeather(lat: String, lon: String): WeatherResponse? {
        return weatherRepository.getWeather(lat, lon)
    }

    override suspend fun getForecast(lat: String, lon: String): ForecastResponse? {
        return weatherRepository.getForecast(lat, lon)
    }

    override suspend fun getTodayForecast(lat: String, lon: String): List<WeatherResponse>? {
        val now = getDateTime(now(), DATE_TIME)
        val getDateToday = getDate(now,DATE_TIME)
        val response = getForecast(lat, lon)

        return response?.forecasts?.filter { weather ->
            weather.date?.let { date ->
                getDate(date, DATE_TIME)
            } == getDateToday
        }
    }

    override suspend fun getFiveDaysForecast(context: Context, lat: String, lon: String): List<ForecastDay>? {
        val response = getForecast(lat, lon)

         return response?.forecasts?.groupBy { weather ->
            weather.date?.let {
                getDate(it, DATE_TIME)
            }
        }?.mapValues { entryDate ->
             val date = "${entryDate.value.first().date}"
             val maxTemp = "${entryDate.value.maxOfOrNull { it.main.tempMax }?.toInt()}º"
             val minTemp = "${entryDate.value.minOfOrNull { it.main.tempMin }?.toInt()}º"
             val speed = "${entryDate.value.map { it.wind.speed }.average().toInt()}"
             val direction = "${entryDate.value.map { it.wind.deg }.getAverageAngle()}"
             ForecastDay(date, maxTemp, minTemp, speed, direction, 1, "")
        }?.values?.toList()
    }
}