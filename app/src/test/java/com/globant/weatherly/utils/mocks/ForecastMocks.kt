package com.globant.weatherly.utils.mocks

import com.globant.weatherly.models.ForecastDay
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

    fun getMockWhenTwoForecastAreToday(): ForecastResponse {
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
                date = "2024-06-20 12:00:00",
                cityName = "La Merced"
            )
        ))
    }

    fun getForecastDays(): List<ForecastDay> {
        return listOf(
            ForecastDay("2024-06-28 15:00:00", "62º", "56º", "2", "O", "04d", "broken clouds"),
            ForecastDay("2024-06-29 00:00:00", "63º", "53º", "2", "", "10n", "light rain"),
            ForecastDay("2024-06-30 00:00:00", "69º", "50º", "2", "SE", "10n", "light rain"),
            ForecastDay("2024-07-01 00:00:00", "65º", "52º", "2", "SE", "10n", "light rain"),
            ForecastDay("2024-07-02 00:00:00", "58º", "51º", "2", "SE", "10n", "light rain"),
            ForecastDay("2024-07-03 00:00:00", "52º", "51º", "3", "ESE", "04n", "overcast clouds")
        )
    }
}