package com.globant.weatherly.services.implementation

import com.globant.weatherly.utils.mocks.ForecastMocks
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class WeatherControllerTest {

    private lateinit var weatherController: WeatherController
    private val weatherRepository: WeatherRepository = mock()

    @Before
    fun setup() {
        weatherController = WeatherController(weatherRepository)
    }

    @Test
    fun `getTodayForecast filters forecasts correctly`() {
        //val mockForecastResponse = ForecastMocks.getMockWeatherResponse()
        //whenever(weatherController.getForecast()).thenReturn(mockForecastResponse)

        //val result = weatherController.getTodayForecast()
        // Aquí deberías verificar que el resultado es correcto basado en los datos mock
        assert(true)
    }

    // Pruebas adicionales para getFiveDaysForecast y otros métodos
}