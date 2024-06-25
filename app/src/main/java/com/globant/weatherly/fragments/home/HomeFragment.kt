package com.globant.weatherly.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.globant.weatherly.R
import com.globant.weatherly.databinding.FragmentHomeBinding
import com.globant.weatherly.extensions.degreesToCardinal
import com.globant.weatherly.models.WeatherResponse
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
    private var nullableBinding: FragmentHomeBinding? = null
    private val binding get() = nullableBinding!!
    private val recyclerAdapter by lazy { GroupAdapter<GroupieViewHolder>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeToViewModel()
    }

    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setListeners()
        handleWeather()
    }

    private fun subscribeToViewModel() {
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

    private fun setListeners() {
        binding.apply {
            swipeHour.setOnRefreshListener {
                swipeHour.isRefreshing = true
                handleWeather()
            }
        }
    }

    private fun handleWeather() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getCombinedWeatherFlow().collect { (weatherResult, forecastResult) ->
                when {
                    (weatherResult.isSuccess && forecastResult.isSuccess) -> {
                        forecastResult.getOrNull()?.let { forecast ->
                            weatherResult.getOrNull()?.let { weather ->
                                onWeatherForecastLoad(forecast, weather)
                            }
                        }
                    }
                    (weatherResult.isFailure && forecastResult.isSuccess) -> {
                        forecastResult.getOrNull()?.let { forecast ->
                            onWeatherError(forecast)
                        }
                    }
                    (weatherResult.isSuccess && forecastResult.isFailure) -> {
                        weatherResult.getOrNull()?.let { weather ->
                            onForecastError(weather)
                        }
                    }
                    (weatherResult.isFailure && forecastResult.isFailure) -> {
                        showLoading(false)
                        showError(true)
                    }
                }
            }
        }
    }

    private fun onWeatherForecastLoad(forecasts: List<WeatherResponse>, currentWeather: WeatherResponse) {
        recyclerAdapter.clear()

        val adapter = GroupAdapter<GroupieViewHolder>()
        currentWeather.let {
            val feelsLike = getString(R.string.feels_like, it.main.feelsLike.toString())
            adapter.add(ItemWeatherHeader(it.cityName ?: "City name", it.wind.deg.degreesToCardinal(), it.wind.speed.toString(), feelsLike, "${it.main.tempMax.toInt()}º" + "/" + "${it.main.tempMin.toInt()}º"))
        }
        forecasts.let {
            adapter.addAll(it.map { weather ->
                ItemTemperatureHour(
                    hour = weather.date?.let { date -> getHourAmPm(date, DATE_TIME) } ?: "1:00PM",
                    temperature = "${weather.main.temp.toInt()}º",
                    iconCode = weather.weather.first().iconCode
                )
            })
            showLoading(false)
        }
        binding.apply {
            recyclerForecast.adapter = adapter
            swipeHour.isRefreshing = false
        }
    }

    private fun onWeatherError(weathers: List<WeatherResponse>) {
        recyclerAdapter.clear()

        weathers.let {
            recyclerAdapter.addAll(it.map { weather ->
                ItemTemperatureHour(
                    hour = weather.date?.let { date -> getHourAmPm(date, DATE_TIME) } ?: "1:00PM",
                    temperature = "${weather.main.temp.toInt()}º",
                    iconCode = weather.weather.first().iconCode
                )
            })
        }
        showLoading(false)
        binding.swipeHour.isRefreshing = false
    }

    private fun onForecastError(currentWeather: WeatherResponse) {
        recyclerAdapter.clear()

        currentWeather.let {
            val feelsLike = getString(R.string.feels_like, it.main.feelsLike.toString())
            recyclerAdapter.add(ItemWeatherHeader(it.cityName ?: "City name", it.wind.deg.degreesToCardinal(), it.wind.speed.toString(), feelsLike, "${it.main.tempMax.toInt()}º" + "/" + "${it.main.tempMin.toInt()}º"))
        }
        showLoading(false)
        binding.swipeHour.isRefreshing = false
    }

    private fun showLoading(show: Boolean) {
        binding.layoutLoading.root.apply {
           visibility = if (show) View.VISIBLE else View.GONE
        }
    }

    private fun showError(show: Boolean) {
        binding.layoutError.root.apply {
            visibility = if (show) View.VISIBLE else View.GONE
        }
    }
}