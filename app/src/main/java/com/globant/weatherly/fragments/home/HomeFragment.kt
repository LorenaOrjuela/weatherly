package com.globant.weatherly.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.globant.weatherly.databinding.FragmentHomeBinding
import com.globant.weatherly.models.WeatherResponse
import com.globant.weatherly.uimodels.forecast.ForecastUiModel
import com.globant.weatherly.uimodels.weather.WeatherUiModel
import com.globant.weatherly.utils.DATE_TIME
import com.globant.weatherly.utils.getHourAmPm
import com.globant.weatherly.viewmodels.home.HomeViewModel
import com.globant.weatherly.views.home.ItemTemperatureHour
import com.globant.weatherly.views.home.ItemWeatherHeader
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private val recyclerAdapter by lazy { GroupAdapter<GroupieViewHolder>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeToViewModel()
        viewModel.getWeather()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun subscribeToViewModel() {
        viewModel.getWeatherUiModels().observe( this@HomeFragment, Observer { handleWeatherUpdate(it) } )
        viewModel.getForecastUiModels().observe( this@HomeFragment, Observer { handleForecastUpdate(it) } )
        viewModel.getShowLoading().observe(this@HomeFragment, Observer { showLoading(it) })
    }

    private fun initViews() {
        val recyclerLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerForecast.apply {
            layoutManager = recyclerLayoutManager
            adapter = recyclerAdapter
            setHasFixedSize(true)
        }
    }

    private fun handleWeatherUpdate(uiModel: WeatherUiModel) {
        when (uiModel) {
            is WeatherUiModel.OnWeatherLoad -> { onWeatherLoad(uiModel.currentWeather) }
            is WeatherUiModel.OnWeatherLoadError -> {  }
        }
    }

    private fun handleForecastUpdate(uiModel: ForecastUiModel) {
        when (uiModel) {
            is ForecastUiModel.OnForecastLoad -> { onForecastLoad(uiModel.weathers, uiModel.currentWeather) }
            is ForecastUiModel.OnForecastLoadError -> {  }
        }
    }

    private fun onWeatherLoad(currentWeather: WeatherResponse) {
        viewModel.getTodayForecast(currentWeather)
    }

    private fun onForecastLoad(weathers: List<WeatherResponse>, currentWeather: WeatherResponse) {
        recyclerAdapter.clear()

        val adapter = GroupAdapter<GroupieViewHolder>()
        currentWeather.let {
            adapter.add(ItemWeatherHeader(it.cityName ?: "City name", it.wind.deg.toString(), it.wind.speed.toString(), it.main.feelsLike.toString(), "${it.main.tempMax.toInt()}º" + "/" + "${it.main.tempMin.toInt()}º"))
        }
        weathers.let {
            adapter.addAll(it.map { weather ->
                ItemTemperatureHour(
                    hour = weather.date?.let { date -> getHourAmPm(date, DATE_TIME) } ?: "1:00PM",
                    temperature = "${weather.main.temp.toInt()}º",
                    icon = 1
                )
            })
            //showLoading(false)
        }
        binding.recyclerForecast.adapter = adapter
    }

    private fun showLoading(show: Boolean) {
        binding.root.apply {
           visibility = if (show) View.VISIBLE else View.GONE
        }
    }
}