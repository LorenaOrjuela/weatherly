package com.globant.weatherly.utils

import android.util.Log
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

const val DATE_TIME = "yyyy-MM-dd HH:mm:ss"
const val HOUR_AM_PM = "hh:mm a"
const val DATE = "yyyy-MM-dd"
const val DAY_MONTH_DAY = "EEEE, MMMM dd"

private val clientTimeZone = ZoneId.systemDefault()

fun now() = LocalDateTime.now()

fun getDateTime(dateTime: LocalDateTime, pattern: String): String {
    val date =
        if (pattern.isNotEmpty()) {
            val formatter = DateTimeFormatter.ofPattern(pattern)
            dateTime.format(formatter)
        } else ""

    return date.toString()
}

fun getDate(dateTimeString: String, pattern: String): String {

    val dateFormatted =
        if (dateTimeString.isNotEmpty() && pattern.isNotEmpty()) {
            val formatter = DateTimeFormatter.ofPattern(pattern)
            val dateTime = LocalDateTime.parse(dateTimeString, formatter)

            val date = dateTime.toLocalDate()

            val formatterDate = DateTimeFormatter.ofPattern(DATE)
            date.format(formatterDate)
        } else ""

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

fun getDateLetters(dateString: String, pattern: String): String {
    Log.i("ppppp", dateString)
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val date = LocalDateTime.parse(dateString, formatter)

    val dateLocal = date.toLocalDate()

    val formatterDate = DateTimeFormatter.ofPattern(DAY_MONTH_DAY)
    val dateFormatted = dateLocal.format(formatterDate)

    return dateFormatted.toString()
}