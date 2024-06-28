package com.globant.weatherly.utils

import android.util.Log
import com.globant.weatherly.Constants.EMPTY
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val DATE_TIME = "yyyy-MM-dd HH:mm:ss"
const val HOUR_AM_PM = "hh:mm a"
const val DATE = "yyyy-MM-dd"
const val DAY_MONTH_DAY = "EEEE, MMMM dd"

fun now() = LocalDateTime.now()

fun getDateTime(dateTime: LocalDateTime, pattern: String): String {
    val date = try {
        if (pattern.isNotEmpty()) {
            val formatter = DateTimeFormatter.ofPattern(pattern)
            dateTime.format(formatter)
        } else EMPTY
    } catch (e: Exception) { "Invalid pattern" }

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
        } catch (e: Exception) { "Inputs are not coherent" }

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
    } catch (E: Exception) { "Inputs are not coherent" }


    return timeAmPm.toString()
}

fun getDateLetters(dateString: String, pattern: String): String {
    val dateFormatted = try {

        val formatter = DateTimeFormatter.ofPattern(pattern)
        val date = LocalDateTime.parse(dateString, formatter)

        val dateLocal = date.toLocalDate()
        val formatterDate = DateTimeFormatter.ofPattern(DAY_MONTH_DAY)
        dateLocal.format(formatterDate)} catch (e: Exception) { e.message }

    return dateFormatted.toString()
}

//TODO: use the next enum to show the day in the forecast Item y forecast screen
enum class DayLetters(val index: Int, val day: String) {
    MONDAY(0, "Monday"),
    TUESDAY(1, "Tuesday"),
    WEDNESDAY(2, "Wednesday"),
    THURSDAY(3, "Thursday"),
    FRIDAY(4, "Friday"),
    SATURDAY(5, "Saturday"),
    SUNDAY(6, "Sunday");

    companion object {
        fun fromIndex(index: Int): DayLetters {
            return values().find { it.index == index }
                ?: throw IllegalArgumentException("Please review if there is an invalid day index: $index")
        }
    }
}

//TODO: use the next enum to show the day in the forecast Item y forecast screen
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

//TODO: use the next enum to show the day in the forecast Item y forecast screen
fun getYearShort(dateTime: String, pattern: String): String {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val parsedDate = LocalDateTime.parse(dateTime, formatter)

    return parsedDate.year.toString().takeLast(2)
}
