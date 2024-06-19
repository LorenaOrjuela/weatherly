package com.globant.weatherly.fragments.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.globant.weatherly.databinding.FragmentForecastBinding
import com.globant.weatherly.databinding.FragmentHomeBinding
import com.globant.weatherly.models.WeatherResponse
import com.globant.weatherly.uimodels.forecast.ForecastUiModel
import com.globant.weatherly.uimodels.weather.WeatherUiModel
import com.globant.weatherly.utils.DATE_TIME
import com.globant.weatherly.utils.getHourAmPm
import com.globant.weatherly.viewmodels.forecast.ForecastViewModel
import com.globant.weatherly.viewmodels.home.HomeViewModel
import com.globant.weatherly.views.home.ItemTemperatureHour
import com.globant.weatherly.views.home.ItemWeatherHeader
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastFragment: Fragment() {

}