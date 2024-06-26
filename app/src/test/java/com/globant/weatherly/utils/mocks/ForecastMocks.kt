package com.globant.weatherly.utils.mocks

import com.globant.weatherly.models.ForecastResponse
import com.globant.weatherly.models.Temperature
import com.globant.weatherly.models.Weather
import com.globant.weatherly.models.WeatherResponse
import com.globant.weatherly.models.WindProperties
import com.globant.weatherly.utils.DATE_TIME
import com.globant.weatherly.utils.getDateTime
import com.globant.weatherly.utils.now

object ForecastMocks {
    fun getMockWeatherResponse(): ForecastResponse {
        val weatherList = listOf(
            Weather(description = "broken clouds", iconCode = "04n"),
            Weather(description = "light rain", iconCode = "10n"),
            Weather(description = "overcast clouds", iconCode = "04d"),
        )

        val temperature = Temperature(
            temp = 285.55,
            feelsLike = 285.33,
            tempMin = 285.55,
            tempMax = 285.55
        )

        val windProperties = WindProperties(
            speed = 1.07,
            deg = 129
        )

        return ForecastResponse( listOf(
            WeatherResponse(
                main = temperature,
                weather = weatherList,
                wind = windProperties,
                date = getDateTime(now(), DATE_TIME),
                cityName = "La Merced"
            ),
            WeatherResponse(
                main = temperature,
                weather = weatherList,
                wind = windProperties,
                date = getDateTime(now(), DATE_TIME),
                cityName = "La Merced"
            ),
            WeatherResponse(
                main = temperature,
                weather = weatherList,
                wind = windProperties,
                date = getDateTime(now(), DATE_TIME),
                cityName = "La Merced"
            )
        ))
    }
}