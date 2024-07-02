package com.globant.weatherly.uimodels.forecast

import com.globant.weatherly.models.ForecastDay

sealed class ForecastUiModel {

    object OnForecastLoading : ForecastUiModel()
    class OnForeCastFiveDaysLoad(val forecasts: List<ForecastDay>): ForecastUiModel()
    object OnForecastFiveDaysLoadError : ForecastUiModel()
}