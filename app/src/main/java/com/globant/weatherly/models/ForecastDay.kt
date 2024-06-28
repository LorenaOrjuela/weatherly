package com.globant.weatherly.models

data class ForecastDay (
    val date: String,
    val maxTemp: String,
    val minTemp: String,
    val speed: String,
    val direction: String,
    val iconCode: String,
    val description: String
)
