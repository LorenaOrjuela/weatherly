package com.globant.weatherly.extensions

import com.globant.weatherly.Constants.EMPTY
import com.globant.weatherly.models.ForecastResponse
import com.globant.weatherly.models.WeatherResponse
import com.globant.weatherly.utils.DATE
import com.globant.weatherly.utils.DATE_TIME
import com.globant.weatherly.utils.getDate
import com.globant.weatherly.utils.getDatePattern
import com.globant.weatherly.utils.now

fun ForecastResponse?.getFutureForecasts(): List<WeatherResponse>? {
    return this?.forecasts?.filter { forecast ->
        forecast.date?.let {
            getDate(it, DATE_TIME) != getDatePattern(now(), DATE)
        } ?: false
    }
}

fun List<WeatherResponse>?.groupForecastsByDate(): Map<String?, List<WeatherResponse>>? {
    return this?.groupBy { weather ->
        weather.date?.let {
            getDate(it, DATE_TIME)
        }
    }
}

fun List<WeatherResponse>.getDate() = first().date ?: EMPTY

fun List<WeatherResponse>.getMaxTemp() = "${maxOfOrNull { it.main.tempMax }?.toInt()}º"

fun List<WeatherResponse>.getMinTemp() = "${minOfOrNull { it.main.tempMin }?.toInt()}º"

fun List<WeatherResponse>.getSpeedAvg() = "${map { it.wind.speed }.average().toInt()}"

fun List<WeatherResponse>.getAngleAvg() = map { it.wind.deg }.getAngleAvg().degreesToCardinal()

fun List<WeatherResponse>.getFrequentDescription(): String {
    return groupingBy {
        it.weather.first().description
    }.eachCount()
        .maxByOrNull {
            it.value
        }?.key ?: EMPTY
}

fun List<WeatherResponse>.getFrequentIcon(): String {
    return groupingBy {
        it.weather.first().iconCode
    }.eachCount()
        .maxByOrNull {
            it.value
        }?.key ?: EMPTY
}