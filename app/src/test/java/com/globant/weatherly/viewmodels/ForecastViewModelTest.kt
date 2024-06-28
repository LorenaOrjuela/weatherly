package com.globant.weatherly.viewmodels

import com.globant.weatherly.services.IWeatherController
import com.globant.weatherly.uimodels.forecast.ForecastUiModel
import com.globant.weatherly.utils.mocks.ForecastMocks.getForecastDays
import com.globant.weatherly.viewmodels.forecast.ForecastViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ForecastViewModelTest {

    //TODO: Review why the test fails wit the error
    //@get:Rule
    //var hiltRule = HiltAndroidRule(this)

    //@get:Rule
    //var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ForecastViewModel
    private val weatherController = mockk<IWeatherController>()

    @Before
    fun setup() {
        //hiltRule.inject()
        viewModel = ForecastViewModel(weatherController)
    }

    @Test
    fun `get five days forecast, when response is not empty, updates LiveData correctly1`() = runBlocking {

        val forecastDays = getForecastDays()

        coEvery { weatherController.getFiveDaysForecast() } returns forecastDays

        viewModel.getFiveDaysForecast(mockk(relaxed = true))

        assertTrue(viewModel.getForecastUiModels().value is ForecastUiModel.OnForeCastFiveDaysLoad)
        assert(true)
    }
}