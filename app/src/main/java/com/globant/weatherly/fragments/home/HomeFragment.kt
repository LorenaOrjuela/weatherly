package com.globant.weatherly.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.globant.weatherly.R
import com.globant.weatherly.databinding.FragmentHomeBinding
import com.globant.weatherly.exceptions.EmptyException
import com.globant.weatherly.models.WeatherResponse
import com.globant.weatherly.viewmodels.home.HomeViewModel
import com.globant.weatherly.views.home.ItemTemperatureHour
import com.globant.weatherly.views.home.ItemWeatherHeader
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
    ): View {
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
        viewModel.getShowLoading().observe(this@HomeFragment) { showLoading(it) }
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
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getCombinedWeatherFlow().collect { (weatherResult, forecastResult) ->
                    if (weatherResult.isSuccess) {
                        if(forecastResult.isSuccess) {
                            forecastResult.getOrNull()?.let { forecast ->
                                weatherResult.getOrNull()?.let { weather ->
                                    onWeatherForecastLoad(forecast, weather)
                                }
                            }
                        } else {
                            weatherResult.getOrNull()?.let { weather ->
                                onWeatherLoad(weather)
                            }
                        }
                    } else {
                        if(forecastResult.isSuccess) {
                            forecastResult.getOrNull()?.let { forecast ->
                                onForecastLoad(forecast)
                            }
                        } else {
                            when (forecastResult.exceptionOrNull()) {
                                is Exception -> showError(true)
                                is EmptyException -> showEmpty(true)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onWeatherForecastLoad(forecasts: List<WeatherResponse>, currentWeather: WeatherResponse) {
        recyclerAdapter.clear()

        val adapter = GroupAdapter<GroupieViewHolder>()

        adapter.add(getWeatherItem(currentWeather))
        adapter.addAll(getForecastItems(forecasts))

        showLoading(false)
        binding.apply {
            recyclerForecast.adapter = adapter
            swipeHour.isRefreshing = false
        }
    }

    private fun onForecastLoad(weathers: List<WeatherResponse>) {
        recyclerAdapter.clear()
        recyclerAdapter.addAll(getForecastItems(weathers))

        showLoading(false)
        binding.swipeHour.isRefreshing = false
    }

    private fun onWeatherLoad(currentWeather: WeatherResponse) {
        recyclerAdapter.clear()
        recyclerAdapter.add(getWeatherItem(currentWeather))

        showLoading(false)
        binding.swipeHour.isRefreshing = false
    }

    private fun getWeatherItem(currentWeather: WeatherResponse): ItemWeatherHeader {
        return currentWeather.let {
            ItemWeatherHeader(
                requireContext(),
                it.cityName,
                it.wind.deg,
                it.wind.speed,
                it.main.feelsLike,
                it.main.tempMax,
                it.main.tempMin
            )
        }
    }

    private fun  getForecastItems(forecasts: List<WeatherResponse>): List<ItemTemperatureHour> {
        return forecasts.map { weather ->
            ItemTemperatureHour(
                weather.date,
                weather.main.temp,
                weather.weather.first().iconCode
            )
        }
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

    private fun showEmpty(show: Boolean) {
        binding.layoutError.apply {
            root.visibility = if (show) View.VISIBLE else View.GONE
            tvMessage.text = getString(R.string.empty)
        }
    }
}