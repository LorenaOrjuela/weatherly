package com.globant.weatherly.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.globant.weatherly.models.ForecastDay
import com.globant.weatherly.services.IWeatherController
import com.globant.weatherly.uimodels.forecast.ForecastUiModel
import com.globant.weatherly.viewmodels.forecast.ForecastViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
@ExperimentalCoroutinesApi
class ForecastViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ForecastViewModel
    private val weatherController = mockk<IWeatherController>()

    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = ForecastViewModel(weatherController)
    }

    @Test
    fun `get five days forecast, when response is not empty, updates LiveData correctly`() = runBlockingTest {
        val forecastDay = ForecastDay("","","","","", 1, "")

        val mockResponse = listOf(forecastDay, forecastDay)
        coEvery { weatherController.getFiveDaysForecast(any()) } returns mockResponse

        viewModel.getFiveDaysForecast(mockk(relaxed = true))

        assertTrue(viewModel.getForecastUiModels().value is ForecastUiModel.OnForeCastFiveDaysLoad)
    }
}