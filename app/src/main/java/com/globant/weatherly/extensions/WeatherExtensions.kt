package com.globant.weatherly.extensions

import com.globant.weatherly.Constants.EMPTY
import com.globant.weatherly.models.WeatherResponse

fun List<WeatherResponse>.getDate() = first().date ?: EMPTY

fun List<WeatherResponse>.getMaxTemp() = "${ maxOfOrNull { it.main.tempMax }?.toInt() }º"

fun List<WeatherResponse>.getMinTemp() = "${ minOfOrNull { it.main.tempMin }?.toInt() }º"

fun List<WeatherResponse>.getSpeedAvg() = "${ map { it.wind.speed }.average().toInt() }"

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