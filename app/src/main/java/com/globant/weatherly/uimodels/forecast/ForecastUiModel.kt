package com.globant.weatherly.uimodels.forecast

import com.globant.weatherly.models.ForecastDay
import com.globant.weatherly.models.ForecastResponse
import com.globant.weatherly.models.WeatherResponse

sealed class ForecastUiModel {

    class OnForecastLoad(val weathers: List<WeatherResponse>, val currentWeather: WeatherResponse) : ForecastUiModel()
    object OnForecastLoadError : ForecastUiModel()

    class OnForeCastFiveDaysLoad(val forecasts: List<ForecastDay>): ForecastUiModel()
    object OnForecastFiveDaysLoadError : ForecastUiModel()
}