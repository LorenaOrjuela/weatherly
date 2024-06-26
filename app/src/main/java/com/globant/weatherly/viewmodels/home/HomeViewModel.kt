package com.globant.weatherly.viewmodels.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.globant.weatherly.exceptions.EmptyException
import com.globant.weatherly.models.WeatherResponse
import com.globant.weatherly.services.IWeatherController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    private val weatherController: IWeatherController): ViewModel()
{
    private val weatherFlow: Flow<Result<WeatherResponse?>> = flow {
        try {
            showLoading.postValue(true)
            val response = weatherController.getWeather()
            if (response != null) {
                emit(Result.success(response))
            } else {
                emit(Result.failure(EmptyException("")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    private val todayForecastFlow: Flow<Result<List<WeatherResponse>?>> = flow {
        try {
            showLoading.postValue(true)
            val response = weatherController.getTodayForecast()
            if (response != null) {
                emit(Result.success(response))
            } else {
                emit(Result.failure(EmptyException("")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun getCombinedWeatherFlow(): Flow<Pair<Result<WeatherResponse?>, Result<List<WeatherResponse>?>>> {
        return weatherFlow.combine(todayForecastFlow) { weatherResult, forecastResult ->
            Pair(weatherResult, forecastResult)
        }
    }

    private val showLoading = MutableLiveData<Boolean>()
    fun getShowLoading(): LiveData<Boolean> {
        return showLoading
    }
}