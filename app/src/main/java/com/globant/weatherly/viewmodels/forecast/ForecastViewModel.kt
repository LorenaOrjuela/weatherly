package com.globant.weatherly.viewmodels.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globant.weatherly.services.IWeatherController
import com.globant.weatherly.uimodels.forecast.ForecastUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val weatherController: IWeatherController
) : ViewModel() {

    private val forecastUiModel = MutableLiveData<ForecastUiModel>()
    fun getForecastUiModels(): LiveData<ForecastUiModel> {
        return forecastUiModel
    }

    fun getFiveDaysForecast() {
        viewModelScope.launch {
            try {
                forecastUiModel.postValue(ForecastUiModel.OnForecastLoading)

                val response = weatherController.getFiveDaysForecast()
                if (!response.isNullOrEmpty()) {
                    forecastUiModel.postValue(ForecastUiModel.OnForeCastFiveDaysLoad(response))
                } else {
                    forecastUiModel.postValue(ForecastUiModel.OnForecastFiveDaysLoadError)
                }
            } catch (e: Exception) {
                forecastUiModel.postValue(ForecastUiModel.OnForecastFiveDaysLoadError)
            }
        }
    }
}