package com.globant.weatherly.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("main") val main: Temperature,
    @SerializedName("weather") val weather: Weather,
    @SerializedName("wind") val wind: WindProperties,
    @SerializedName("dt_txt") val date: String
)

data class ForecastResponse(
    @SerializedName("list") val forecasts: List<WeatherResponse>
)

data class Temperature(
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
)

data class WindProperties(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val deg: Int
)

data class Weather(
    @SerializedName("description") val description: String,
    @SerializedName("icon") val deg: String
)

