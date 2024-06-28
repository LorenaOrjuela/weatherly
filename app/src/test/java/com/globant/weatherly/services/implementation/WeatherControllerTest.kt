package com.globant.weatherly.services.implementation

import com.globant.weatherly.utils.DATE_TIME
import com.globant.weatherly.utils.getDateTime
import com.globant.weatherly.utils.mocks.ForecastMocks
import com.globant.weatherly.utils.now
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class WeatherControllerTest {

    private lateinit var weatherController: WeatherController
    private val weatherRepository: WeatherRepository = mockk()

    @Before
    fun setup() {
        weatherController = WeatherController(weatherRepository)
    }

    @Test
    fun `getTodayForecast filters forecasts correctly when all forecasts are today`() {
        val forecastResponse = ForecastMocks.getMockWeatherResponse()
        val expectedSize = forecastResponse.forecasts.size

        coEvery { weatherController.getForecast() } returns forecastResponse

        val forecasts = runBlocking { weatherController.getTodayForecast() }

        assertNotNull(forecasts)
        forecasts?.let {
            assertEquals(expectedSize, forecasts.size)
        } ?: assert(false)
    }

    @Test
    fun `getTodayForecast filters forecasts correctly when 2 forecasts are today`() {
        val forecastResponse = ForecastMocks.getMockWhenTwoForecastAreToday()
        val expectedSize = forecastResponse.forecasts.filter { forecast ->
            forecast.date == getDateTime(now(), DATE_TIME)
        }.size

        coEvery { weatherController.getForecast() } returns forecastResponse

        val forecasts = runBlocking { weatherController.getTodayForecast() }

        assertNotNull(forecasts)
        forecasts?.let {
            assertEquals(expectedSize, forecasts.size)
        } ?: assert(false)
    }

    //TODO test cases related to getFiveDaysForecast
}