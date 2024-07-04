package com.globant.weatherly.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.globant.weatherly.TestConstants.CURRENT_DATE_TEST
import com.globant.weatherly.TestConstants.DATE_UTILS_CLASS
import com.globant.weatherly.services.implementation.WeatherController
import com.globant.weatherly.services.implementation.WeatherRepository
import com.globant.weatherly.uimodels.forecast.ForecastUiModel
import com.globant.weatherly.utils.DATE
import com.globant.weatherly.utils.getDatePattern
import com.globant.weatherly.utils.mocks.ForecastMocks.getForecastDaysSuccessful
import com.globant.weatherly.utils.mocks.ForecastMocks.getMockFiveDaysThreeHoursResponse
import com.globant.weatherly.viewmodels.forecast.ForecastViewModel
import com.jraska.livedata.test
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ForecastViewModelTest {

    private lateinit var weatherController: WeatherController
    private lateinit var viewModel: ForecastViewModel
    private val weatherRepository: WeatherRepository = mockk<WeatherRepository>(relaxed = true)

    @get:Rule
    var rule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        weatherController = WeatherController(weatherRepository)
        viewModel = ForecastViewModel(weatherController)
        mockkStatic(DATE_UTILS_CLASS)
    }

    @Test
    fun `get days forecast, when response is successful, updates LiveData correctly and response is coherent`() =
        runTest {
            val weatherResponseDays = getMockFiveDaysThreeHoursResponse()
            val observer = viewModel.getForecastUiModels().test()
            val forecastExpected = getForecastDaysSuccessful()

            coEvery { weatherController.getForecast() } returns weatherResponseDays
            every { getDatePattern(any(), DATE) } returns CURRENT_DATE_TEST

            viewModel.getFiveDaysForecast()

            observer.assertValue {
                it is ForecastUiModel.OnForeCastFiveDaysLoad && it.forecasts == forecastExpected
            }
        }

    @Test
    fun `get days forecast, when response is null, updates LiveData error`() = runTest {
        val observer = viewModel.getForecastUiModels().test()

        coEvery { weatherController.getForecast() } returns null

        viewModel.getFiveDaysForecast()

        observer.assertValue {
            it is ForecastUiModel.OnForecastFiveDaysLoadError
        }
    }
}