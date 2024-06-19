package com.globant.weatherly.uimodels.weather

import com.globant.weatherly.models.WeatherResponse

sealed class WeatherUiModel {

    class OnWeatherLoad(val currentWeather: WeatherResponse) : WeatherUiModel()
    object OnWeatherLoadError : WeatherUiModel()
}