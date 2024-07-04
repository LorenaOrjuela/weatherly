package com.globant.weatherly.services.implementation

import com.globant.weatherly.extensions.getAngleAvg
import com.globant.weatherly.extensions.getDate
import com.globant.weatherly.extensions.getFrequentDescription
import com.globant.weatherly.extensions.getFrequentIcon
import com.globant.weatherly.extensions.getFutureForecasts
import com.globant.weatherly.extensions.getMaxTemp
import com.globant.weatherly.extensions.getMinTemp
import com.globant.weatherly.extensions.getSpeedAvg
import com.globant.weatherly.extensions.groupForecastsByDate
import com.globant.weatherly.models.ForecastDay
import com.globant.weatherly.models.ForecastResponse
import com.globant.weatherly.models.WeatherResponse
import com.globant.weatherly.services.IWeatherController
import com.globant.weatherly.utils.DATE_TIME
import com.globant.weatherly.utils.getDate
import com.globant.weatherly.utils.getDateLetters
import com.globant.weatherly.utils.getDatePattern
import com.globant.weatherly.utils.now
import javax.inject.Inject

class WeatherController @Inject constructor(
    private val weatherRepository: WeatherRepository
) : IWeatherController {

    override suspend fun getWeather(): WeatherResponse? {
        return weatherRepository.getWeather()
    }

    override suspend fun getForecast(): ForecastResponse? {
        return weatherRepository.getForecast()
    }

    override suspend fun getTodayForecast(): List<WeatherResponse>? {
        val now = getDatePattern(now(), DATE_TIME)
        val getDateToday = getDate(now, DATE_TIME)
        val response = getForecast()

        return response?.forecasts?.filter { weather ->
            weather.date?.let { date ->
                getDate(date, DATE_TIME)
            } == getDateToday
        }
    }

    override suspend fun getFiveDaysForecast(): List<ForecastDay>? {
        val response = getForecast()

        return response?.getFutureForecasts()?.groupForecastsByDate()
            ?.mapValues { entryDate ->
                val weatherGrouped = entryDate.value
                val date = getDateLetters(weatherGrouped.getDate(), DATE_TIME)
                val maxTemp = weatherGrouped.getMaxTemp()
                val minTemp = weatherGrouped.getMinTemp()
                val speed = weatherGrouped.getSpeedAvg()
                val direction = weatherGrouped.getAngleAvg()
                val description = weatherGrouped.getFrequentDescription()
                val iconCode = weatherGrouped.getFrequentIcon()

                ForecastDay(date, maxTemp, minTemp, speed, direction, iconCode, description)
            }?.values?.toList()
    }
}