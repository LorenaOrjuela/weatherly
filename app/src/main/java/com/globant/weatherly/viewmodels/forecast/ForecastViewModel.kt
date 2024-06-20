package com.globant.weatherly.viewmodels.forecast

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globant.weatherly.services.IWeatherController
import com.globant.weatherly.uimodels.forecast.ForecastUiModel
import com.globant.weatherly.utils.LocationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor (
    private val weatherController: IWeatherController
): ViewModel() {

    private val forecastUiModel = MutableLiveData<ForecastUiModel>()
    fun getForecastUiModels(): LiveData<ForecastUiModel> {
        return forecastUiModel
    }

    private val showLoading = MutableLiveData<Boolean>()
    fun getShowLoading(): LiveData<Boolean> {
        return showLoading
    }

    fun getFiveDaysForecast(context: Context) {
        viewModelScope.launch {
            try {
                showLoading.postValue(true)

                val location = LocationUtils.getLocation(context)
                val response = weatherController.getFiveDaysForecast(context, location.latitude.toString(), location.longitude.toString())
                response?.let {
                    if (response.isNotEmpty()) {
                        forecastUiModel.postValue(ForecastUiModel.OnForeCastFiveDaysLoad(response))
                    } else {
                        forecastUiModel.postValue(ForecastUiModel.OnForecastFiveDaysLoadError)
                    }
                }
            } catch (e: Exception) {
                forecastUiModel.postValue(ForecastUiModel.OnForecastFiveDaysLoadError)
            }
        }
    }
}