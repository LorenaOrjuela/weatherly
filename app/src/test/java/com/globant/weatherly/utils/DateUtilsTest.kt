package com.globant.weatherly.utils

import com.globant.weatherly.Constants.EMPTY
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.LocalDateTime

class DateUtilsTest {

    @Test
    fun `achieve dateTime successfully when inputs are expected`() {
        val localDateTime = LocalDateTime.of(2024, 6, 20, 12, 0, 0)
        val pattern = "yyyy-MM-dd HH:mm:ss"
        val expected = "2024-06-20 12:00:00"

        val dateTime = getDatePattern(localDateTime, pattern)

        assertEquals(expected, dateTime)
    }

    @Test
    fun `achieve dateTime Invalid pattern when pattern is not coherent`() {
        val localDateTime = LocalDateTime.of(2024, 6, 20, 12, 0, 0)
        val pattern = "yyyy-MM-dd HH:mm:sss"
        val expected = "Invalid pattern"

        val dateTime = getDatePattern(localDateTime, pattern)

        assertEquals(expected, dateTime)
    }

    @Test
    fun `achieve dateTime empty when pattern is empty`() {
        val localDateTime = LocalDateTime.of(2024, 6, 20, 12, 0, 0)
        val pattern = EMPTY
        val expected = EMPTY

        val dateTime = getDatePattern(localDateTime, pattern)

        assertEquals(expected, dateTime)
    }

    @Test
    fun `achieve date successfully when inputs are expected`() {
        val dateTimeString = "2024-06-20 12:00:00"
        val pattern = "yyyy-MM-dd HH:mm:ss"
        val expected = "2024-06-20"

        val date = getDate(dateTimeString, pattern)

        assertEquals(expected, date)
    }

    @Test
    fun `achieve date Invalid pattern when inputs are not coherent`() {
        val dateTimeString = "2024-06-20 12:00:000"
        val pattern = "yyyy-MM-dd HH:mm:sss"
        val expected = "Inputs are not coherent"

        val date = getDate(dateTimeString, pattern)

        assertEquals(expected, date)
    }

    @Test
    fun `achieve empty date when date and patterns are empty`() {
        val dateTimeString = EMPTY
        val pattern = EMPTY
        val expected = EMPTY

        val date = getDate(dateTimeString, pattern)

        assertEquals(expected, date)
    }

    @Test
    fun `pm am hour when date time and pattern are valid`() {
        val formatter = "yyyy-MM-dd HH:mm:ss"
        val dateTime = "2024-06-20 02:31:20"

        val hourAmPm = getHourAmPm(dateTime, formatter)

        assertEquals("02:31 AM", hourAmPm)
    }

    @Test
    fun `pm am hour Invalid pattern when inputs are not coherent`() {
        val formatter = "yyyy-MM-dd HH:mm:sss"
        val dateTime = "2024-06-20 02:31:200"
        val expected = "Inputs are not coherent"

        val hourAmPm = getHourAmPm(dateTime, formatter)

        assertEquals(expected, hourAmPm)
    }

    @Test
    fun `pm am hour when date time and pattern are empty`() {
        val formatter = EMPTY
        val dateTime = EMPTY

        val hourAmPm = getHourAmPm(dateTime, formatter)

        assertEquals(EMPTY, hourAmPm)
    }

    @Test
    fun `getDateLetters correctly when inputs are coherent`() {
        val dateTime = "2024-06-20 02:31:20"
        val expected = "Thursday, June 20"

        val dateLetters = getDateLetters(dateTime, DATE_TIME)

        assertEquals(expected, dateLetters)
    }

    @Test
    fun `getDateLetters correctly when inputs are not coherent`() {
        val formatter = "yyyy-MM-dd HH:mm:sss"
        val dateTime = "2024-06-20 02:31:200"
        val expected = "Inputs are not coherent"

        val dateLetters = getDateLetters(dateTime, formatter)

        assertEquals(expected, dateLetters)
    }
}