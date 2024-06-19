package com.globant.weatherly.viewmodels.forecast

import androidx.lifecycle.ViewModel
import com.globant.weatherly.services.IWeatherController
import javax.inject.Inject

class ForecastViewModel @Inject constructor (
    private val weatherController: IWeatherController
): ViewModel() {

}