package com.globant.weatherly.extensions

import com.globant.weatherly.models.WeatherResponse
import com.globant.weatherly.utils.DATE_TIME
import com.globant.weatherly.utils.getHourAmPm

fun WeatherResponse.getAmPmHour() = date?.let {
        date -> getHourAmPm(date, DATE_TIME)
} ?: "1:00PM"