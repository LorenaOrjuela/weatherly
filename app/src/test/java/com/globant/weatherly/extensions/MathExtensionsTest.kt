package com.globant.weatherly.extensions

import junit.framework.TestCase.assertEquals
import org.junit.Test
import junit.framework.TestCase.assertTrue

class MathExtensionsTest {

    @Test
    fun `get average angle in when the list contains coherent info`() {

        val coherentAngles: List<Int> = listOf(20, -720, 35, -67, 720, 800)
        val angleAverage = coherentAngles.getAverageAngle()

        assertEquals(12.770848172356189, angleAverage)
    }

    @Test
    fun `get average angle in Double when the list contains coherent info`() {

        val coherentAngles: List<Int> = listOf(20, -720, 35, -67, 720, 800)
        val angleAverage = coherentAngles.getAverageAngle()

        assertTrue("angleAverage average should  be in double",angleAverage is Double)
    }

    @Test
    fun `get average angle between 0 and 360 when the list contains coherent info`() {

        val coherentAngles: List<Int> = listOf(20, -720, 35, -67, 720, 800)
        val angleAverage = coherentAngles.getAverageAngle()

        assertTrue("angleAverage should between the limits",angleAverage in 0.0..360.0)
    }

    @Test
    fun `converting degrees to cardinal when degrees provided is a Double coherent`(){

        val degrees = 50.00
        val cardinal = degrees.degreesToCardinal()

        assertEquals("NE", cardinal)
    }

    @Test
    fun `converting degrees to cardinal when degrees provided is a Double negative`(){

        val degrees = -50.00
        val cardinal = degrees.degreesToCardinal()

        assertEquals("NO", cardinal)
    }

    @Test
    fun `converting degrees to cardinal when degrees provided is an int coherent`(){

        val degrees = 50
        val cardinal = degrees.degreesToCardinal()

        assertEquals("NE", cardinal)
    }

    @Test
    fun `converting degrees to cardinal when degrees provided is a int negative`(){

        val degrees = -50
        val cardinal = degrees.degreesToCardinal()

        assertEquals("NO", cardinal)
    }
}