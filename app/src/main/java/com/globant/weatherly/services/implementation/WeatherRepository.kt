package com.globant.weatherly.services.implementation

import com.globant.weatherly.models.ForecastResponse
import com.globant.weatherly.models.WeatherResponse
import com.globant.weatherly.services.IWeatherRepository
import com.globant.weatherly.services.WeatherServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherServices: WeatherServices
): IWeatherRepository {

    override suspend fun getWeather(lat: String, lon: String): WeatherResponse? = withContext(Dispatchers.IO) {
        val response = weatherServices.getWeather(lat,lon)
        return@withContext if (response.isSuccessful) {
            response.body()
        } else {
            throw Exception()
        }
    }

    override suspend fun getForecast(lat: String, lon: String): ForecastResponse? = withContext(Dispatchers.IO) {
        val response = weatherServices.getForecast(lat,lon)
        return@withContext if (response.isSuccessful) {
            response.body()
        } else {
            throw Exception()
        }
    }
}