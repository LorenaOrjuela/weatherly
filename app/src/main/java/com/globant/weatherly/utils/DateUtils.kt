package com.globant.weatherly.utils

import com.globant.weatherly.Constants.EMPTY
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val DATE_TIME = "yyyy-MM-dd HH:mm:ss"
const val HOUR_AM_PM = "hh:mm a"
const val DATE = "yyyy-MM-dd"

fun now() = LocalDateTime.now()

fun getDatePattern(dateTime: LocalDateTime, pattern: String): String {
    val date = try {
        if (pattern.isNotEmpty()) {
            val formatter = DateTimeFormatter.ofPattern(pattern)
            dateTime.format(formatter)
        } else EMPTY
    } catch (e: Exception) {
        "Invalid pattern"
    }

    return date.toString()
}

fun getDate(dateTimeString: String, pattern: String): String {

    val dateFormatted =
        try {
            if (dateTimeString.isNotEmpty() && pattern.isNotEmpty()) {
                val formatter = DateTimeFormatter.ofPattern(pattern)
                val dateTime = LocalDateTime.parse(dateTimeString, formatter)

                val date = dateTime.toLocalDate()

                val formatterDate = DateTimeFormatter.ofPattern(DATE)
                date.format(formatterDate)
            } else EMPTY
        } catch (e: Exception) {
            "Inputs are not coherent"
        }

    return dateFormatted.toString()
}

fun getHourAmPm(dateTimeString: String, pattern: String): String {

    val timeAmPm = try {
        if (dateTimeString.isNotEmpty() && pattern.isNotEmpty()) {
            val formatter = DateTimeFormatter.ofPattern(pattern)
            val dateTime = LocalDateTime.parse(dateTimeString, formatter)

            val time = dateTime.toLocalTime()

            val formatterAmPm = DateTimeFormatter.ofPattern(HOUR_AM_PM)
            time.format(formatterAmPm)
        } else EMPTY
    } catch (E: Exception) {
        "Inputs are not coherent"
    }

    return timeAmPm.toString()
}

fun getDateLetters(dateString: String, pattern: String): String {

    val dateLetters = try {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        val date = LocalDateTime.parse(dateString, formatter)

        val dayOfWeek = date.dayOfWeek.value
        val month = date.month.value
        val day = date.dayOfMonth

        "${DayLetters.fromIndex(dayOfWeek).day}, ${MonthLetters.fromIndex(month).month} $day"
    } catch (e: Exception) {
        "Inputs are not coherent"
    }

    return dateLetters
}

enum class DayLetters(val index: Int, val day: String) {
    MONDAY(1, "Monday"),
    TUESDAY(2, "Tuesday"),
    WEDNESDAY(3, "Wednesday"),
    THURSDAY(4, "Thursday"),
    FRIDAY(5, "Friday"),
    SATURDAY(6, "Saturday"),
    SUNDAY(7, "Sunday");

    companion object {
        fun fromIndex(index: Int): DayLetters {
            return values().find { it.index == index }
                ?: throw IllegalArgumentException("Please review if there is an invalid day index: $index")
        }
    }
}

enum class MonthLetters(val index: Int, val month: String) {
    JANUARY(1, "January"),
    FEBRUARY(2, "February"),
    MARCH(3, "March"),
    APRIL(4, "April"),
    MAY(5, "May"),
    JUNE(6, "June"),
    JULY(7, "July"),
    AUGUST(8, "August"),
    SEPTEMBER(9, "September"),
    OCTOBER(10, "October"),
    NOVEMBER(11, "November"),
    DECEMBER(12, "December");

    companion object {
        fun fromIndex(index: Int): MonthLetters {
            return values().find { it.index == index }
                ?: throw IllegalArgumentException("Please review if there is an invalid day index: $index")
        }
    }
}
