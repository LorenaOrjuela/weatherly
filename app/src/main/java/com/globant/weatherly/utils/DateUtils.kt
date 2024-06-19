package com.globant.weatherly.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

const val DATE_TIME = "yyyy-MM-dd HH:mm:ss"
const val HOUR_AM_PM = "hh:mm a"
const val DATE = "yyyy-MM-dd"

private val clientTimeZone = ZoneId.systemDefault()

fun now() = LocalDateTime.now()

fun getDateTime(dateTime: LocalDateTime, pattern: String): String {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val dateTime = dateTime.format(formatter)

    return dateTime.toString()
}

fun getDate(dateTimeString: String, pattern: String): String {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val dateTime = LocalDateTime.parse(dateTimeString, formatter)

    val date = dateTime.toLocalDate()

    val formatterDate = DateTimeFormatter.ofPattern(DATE)
    val dateFormatted = date.format(formatterDate)

    return dateFormatted.toString()
}

fun getHourAmPm(dateTimeString: String, pattern: String): String {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val dateTime = LocalDateTime.parse(dateTimeString, formatter)

    val time = dateTime.toLocalTime()

    val formatterAmPm = DateTimeFormatter.ofPattern(HOUR_AM_PM)
    val timeAmPm = time.format(formatterAmPm)

    return timeAmPm.toString()
}