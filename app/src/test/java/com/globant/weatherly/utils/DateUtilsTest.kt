package com.globant.weatherly.utils

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.LocalDateTime

class DateUtilsTest {

    @Test
    fun achieve_date_time_successfully_when_inputs_are_expected() {
        val localDateTime = LocalDateTime.of(2024, 6, 20, 12, 0, 0)
        val pattern = "yyyy-MM-dd HH:mm:ss"
        val expected = "2024-06-20 12:00:00"

        val actual = getDateTime(localDateTime, pattern)

        assertEquals(expected, actual)
    }

    @Test
    fun achieve_empty_when_pattern_is_empty() {
        val localDateTime = LocalDateTime.of(2024, 6, 20, 12, 0, 0)
        val pattern = ""
        val expected = ""

        val actual = getDateTime(localDateTime, pattern)

        assertEquals(expected, actual)
    }

    @Test
    fun achieve_date_successfully_when_inputs_are_expected() {
        val dateTimeString = "2024-06-20 12:00:00"
        val pattern = "yyyy-MM-dd HH:mm:ss"
        val expected = "2024-06-20"
        val actual = getDate(dateTimeString, pattern)

        assertEquals(expected, actual)
    }

    @Test
    fun achieve_date_empty_when_date_and_patterns_are_empty() {
        val dateTimeString = ""
        val pattern = ""
        val expected = ""
        val actual = getDate(dateTimeString, pattern)

        assertEquals(expected, actual)
    }
}