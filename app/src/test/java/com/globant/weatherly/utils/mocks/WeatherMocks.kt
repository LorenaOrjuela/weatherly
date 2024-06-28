package com.globant.weatherly.utils.mocks

import com.globant.weatherly.models.Temperature
import com.globant.weatherly.models.Weather
import com.globant.weatherly.models.WeatherResponse
import com.globant.weatherly.models.WindProperties
import com.globant.weatherly.utils.DATE_TIME
import com.globant.weatherly.utils.getDateTime
import com.globant.weatherly.utils.now

object WeatherMocks {
    private val mockWeather = Weather(description = "broken clouds", iconCode = "04n")
    private val mockTemperature = Temperature(temp = 57.2, feelsLike = 57.33, tempMin = 57.2, tempMax = 57.2)
    private val mockWindProperties = WindProperties(speed = 4.61, deg = 270)
    val mockWeatherCurrentResponse = WeatherResponse(
        main = mockTemperature,
        weather = listOf(mockWeather),
        wind = mockWindProperties,
        date = getDateTime(now(), DATE_TIME),
        cityName = "La Merced"
    )
}