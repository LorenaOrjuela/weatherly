package com.globant.weatherly.services.implementation

import com.globant.weatherly.extensions.degreesToCardinal
import com.globant.weatherly.extensions.getAverageAngle
import com.globant.weatherly.models.ForecastDay
import com.globant.weatherly.models.ForecastResponse
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
            } == getDateToday
        }
    }

    override suspend fun getFiveDaysForecast(): List<ForecastDay>? {
        val response = getForecast()

         return response?.forecasts?.groupBy { weather ->
            weather.date?.let {
                getDate(it, DATE_TIME)
            }
        }?.mapValues { entryDate ->
             val date = "${entryDate.value.first().date}"
             val maxTemp = "${entryDate.value.maxOfOrNull { it.main.tempMax }?.toInt()}º"
             val minTemp = "${entryDate.value.minOfOrNull { it.main.tempMin }?.toInt()}º"
             val speed = "${entryDate.value.map { it.wind.speed }.average().toInt()}"
             val direction = entryDate.value.map { it.wind.deg }.getAverageAngle().degreesToCardinal()
             val description = entryDate.value.first().weather.first().description
             val iconCode = entryDate.value.first().weather.first().iconCode
             ForecastDay(date, maxTemp, minTemp, speed, direction, iconCode, description)
        }?.values?.toList()
    }
}