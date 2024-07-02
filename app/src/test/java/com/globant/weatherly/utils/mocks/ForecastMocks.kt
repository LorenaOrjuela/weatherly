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

    fun getForecastWhenNoForecastIsToday(): ForecastResponse {
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
                date = "2024-06-22 12:00:00",
                cityName = "La Merced"
            ),
            WeatherResponse(
                main = temperature,
                weather = weatherList,
                wind = windProperties,
                date = "2024-06-21 12:00:00",
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

    fun getMockFiveDaysThreeHoursResponse(): ForecastResponse {
        val forecasts = listOf(
            WeatherResponse(
                Temperature(temp = 55.22, feelsLike = 54.63, tempMin = 55.22, tempMax = 58.44),
                weather = listOf(Weather(description = "light rain", iconCode = "10d")),
                wind = WindProperties(speed = 3.94, deg = 141),
                date = "2024-07-02 00:00:00",
                cityName = "La Merced"
            ),
            WeatherResponse(
                main = Temperature(temp = 63.07, feelsLike = 62.37, tempMin = 63.07, tempMax = 67.80),
                weather = listOf(Weather(description = "light rain", iconCode = "10d")),
                wind = WindProperties(speed = 3.09, deg = 162),
                date = "2024-07-02 03:00:00",
                cityName = "La Merced"
            ),
            WeatherResponse(
                main = Temperature(temp = 61.83, feelsLike = 61.38, tempMin = 61.83, tempMax = 61.83),
                weather = listOf(Weather(description = "light rain", iconCode = "10d")),
                wind = WindProperties(speed = 3.49, deg = 178),
                date = "2024-07-03 00:00:00",
                cityName = "La Merced"
            ),
            WeatherResponse(
                main = Temperature(temp = 54.75, feelsLike = 54.34, tempMin = 54.75, tempMax = 54.75),
                weather = listOf(Weather(description = "light rain", iconCode = "10n")),
                wind = WindProperties(speed = 3.69, deg = 140),
                date = "2024-07-03 03:00:00",
                cityName = "La Merced"
            ),
            WeatherResponse(
                main = Temperature(temp = 52.14, feelsLike = 51.57, tempMin = 52.14, tempMax = 52.14),
                weather = listOf(Weather(description = "broken clouds", iconCode = "04n")),
                wind = WindProperties(speed = 3.91, deg = 118),
                date = "2024-07-04 00:00:00",
                cityName = "La Merced"
            ),
            WeatherResponse(
                main = Temperature(temp = 53.14, feelsLike = 53.57, tempMin = 54.14, tempMax = 50.14),
                weather = listOf(Weather(description = "light rain", iconCode = "10n")),
                wind = WindProperties(speed = 3.91, deg = 118),
                date = "2024-07-04 03:00:00",
                cityName = "La Merced"
            ),
            WeatherResponse(
                main = Temperature(temp = 53.14, feelsLike = 53.57, tempMin = 54.14, tempMax = 50.14),
                weather = listOf(Weather(description = "light rain", iconCode = "10d")),
                wind = WindProperties(speed = 3.91, deg = 118),
                date = "2024-07-05 00:00:00",
                cityName = "La Merced"
            ),
            WeatherResponse(
                main = Temperature(temp = 52.14, feelsLike = 52.57, tempMin = 53.14, tempMax = 49.14),
                weather = listOf(Weather(description = "light rain", iconCode = "10d")),
                wind = WindProperties(speed = 3.91, deg = 118),
                date = "2024-07-05 03:00:00",
                cityName = "La Merced"
            ),
            WeatherResponse(
                main = Temperature(temp = 52.14, feelsLike = 51.57, tempMin = 52.14, tempMax = 52.14),
                weather = listOf(Weather(description = "broken clouds", iconCode = "04n")),
                wind = WindProperties(speed = 3.91, deg = 118),
                date = "2024-07-06 00:00:00",
                cityName = "La Merced"
            ),
            WeatherResponse(
                main = Temperature(temp = 63.07, feelsLike = 62.37, tempMin = 63.07, tempMax = 67.80),
                weather = listOf(Weather(description = "light rain", iconCode = "10d")),
                wind = WindProperties(speed = 3.09, deg = 162),
                date = "2024-07-06 03:00:00",
                cityName = "La Merced"
            ),
            WeatherResponse(
                main = Temperature(temp = 51.30, feelsLike = 52.40, tempMin = 53.14, tempMax = 50.00),
                weather = listOf(Weather(description = "broken clouds", iconCode = "04n")),
                wind = WindProperties(speed = 3.91, deg = 118),
                date = "2024-07-07 00:00:00",
                cityName = "La Merced"
            ),
            WeatherResponse(
                main = Temperature(temp = 63.07, feelsLike = 62.37, tempMin = 63.07, tempMax = 67.80),
                weather = listOf(Weather(description = "broken clouds", iconCode = "04n")),
                wind = WindProperties(speed = 4.09, deg = 160),
                date = "2024-07-07 03:00:00",
                cityName = "La Merced"
            ),
            WeatherResponse(
                main = Temperature(temp = 61.07, feelsLike = 62.03, tempMin = 64.09, tempMax = 65.10),
                weather = listOf(Weather(description = "light rain", iconCode = "10d")),
                wind = WindProperties(speed = 4.09, deg = 160),
                date = "2024-07-07 06:00:00",
                cityName = "La Merced"
            )
        )
        return ForecastResponse(forecasts = forecasts)
    }


    fun getForecastDaysSuccessful(): List<ForecastDay> {
        return listOf(
            ForecastDay(date = "Tuesday, July 24", maxTemp = "67º", minTemp = "55º", speed = "3", direction = "SSE", iconCode = "10d", description = "light rain"),
            ForecastDay(date = "Wednesday, July 24", maxTemp = "61º", minTemp = "54º", speed = "3", direction = "SSE", iconCode = "10d", description = "light rain"),
            ForecastDay(date = "Thursday, July 24", maxTemp = "52º", minTemp = "52º", speed = "3", direction = "ESE", iconCode = "04n", description = "broken clouds"),
            ForecastDay(date = "Friday, July 24", maxTemp = "50º", minTemp = "53º", speed = "3", direction = "ESE", iconCode = "10d", description = "light rain"),
            ForecastDay(date = "Saturday, July 24", maxTemp = "67º", minTemp = "52º", speed = "3", direction = "SE", iconCode = "04n", description = "broken clouds"),
            ForecastDay(date = "Sunday, July 24", maxTemp = "67º", minTemp = "53º", speed = "4", direction = "SSE", iconCode = "04n", description = "broken clouds")
        )
    }
}