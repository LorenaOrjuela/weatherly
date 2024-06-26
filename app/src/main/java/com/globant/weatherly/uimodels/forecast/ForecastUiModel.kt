package com.globant.weatherly.uimodels.forecast

import com.globant.weatherly.models.ForecastDay

sealed class ForecastUiModel {

    class OnForecastLoading(val loading: Boolean) : ForecastUiModel()
    class OnForeCastFiveDaysLoad(val forecasts: List<ForecastDay>): ForecastUiModel()
    object OnForecastFiveDaysLoadError : ForecastUiModel()
}