package com.globant.weatherly.viewmodels.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globant.weatherly.models.WeatherResponse
import com.globant.weatherly.services.IWeatherController
import com.globant.weatherly.uimodels.forecast.ForecastUiModel
import com.globant.weatherly.uimodels.weather.WeatherUiModel
import com.globant.weatherly.utils.LocationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    private val weatherController: IWeatherController): ViewModel()
{
    private val forecastUiModel = MutableLiveData<ForecastUiModel>()
    fun getForecastUiModels(): LiveData<ForecastUiModel> {
        return forecastUiModel
    }

    private val weatherUiModel = MutableLiveData<WeatherUiModel>()
    fun getWeatherUiModels(): LiveData<WeatherUiModel> {
        return weatherUiModel
    }

    private val showLoading = MutableLiveData<Boolean>()
    fun getShowLoading(): LiveData<Boolean> {
        return showLoading
    }

    fun getWeather(context: Context) {
        viewModelScope.launch {
            val location = LocationUtils.getLocation(context)

            showLoading.postValue(true)
            val response = weatherController.getWeather(location.latitude.toString(), location.longitude.toString())
            if (response != null) {
                weatherUiModel.postValue(WeatherUiModel.OnWeatherLoad(response))
            } else {
                weatherUiModel.postValue(WeatherUiModel.OnWeatherLoadError)
            }
        }
    }

    fun getTodayForecast(currentWeather: WeatherResponse, context: Context) {
        viewModelScope.launch {
            val location = LocationUtils.getLocation(context)

            showLoading.postValue(true)
            val response = weatherController.getTodayForecast(location.latitude.toString(), location.longitude.toString())
            response?.let {
                if (response.isNotEmpty()) {
                    forecastUiModel.postValue(ForecastUiModel.OnForecastLoad(response, currentWeather))
                } else {
                    forecastUiModel.postValue(ForecastUiModel.OnForecastLoadError)
                }
            }
        }
    }
}