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
import com.globant.weatherly.R
import com.globant.weatherly.databinding.FragmentHomeBinding
import com.globant.weatherly.extensions.degreesToCardinal
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
    private var nullableBinding: FragmentHomeBinding? = null
    private val binding get() = nullableBinding!!
    private val recyclerAdapter by lazy { GroupAdapter<GroupieViewHolder>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeToViewModel()
        viewModel.getWeather(requireContext())
    }

    override fun onCreateView(
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

    private fun setListeners() {
        binding.apply {
            swipeHour.setOnRefreshListener {
                swipeHour.isRefreshing = true
                viewModel.getWeather(requireContext())
            }
        }
    }

    private fun handleWeatherUpdate(uiModel: WeatherUiModel) {
        when (uiModel) {
            is WeatherUiModel.OnWeatherLoad -> { onWeatherLoad(uiModel.currentWeather) }
            is WeatherUiModel.OnWeatherLoadError -> {
                showLoading(false)
                showError(true)
            }
        }
    }

    private fun handleForecastUpdate(uiModel: ForecastUiModel) {
        when (uiModel) {
            is ForecastUiModel.OnForecastLoad -> { onForecastLoad(uiModel.weathers, uiModel.currentWeather) }
            is ForecastUiModel.OnForecastLoadError -> {
                showLoading(false)
                showError(true)
            }
            is ForecastUiModel.OnForeCastFiveDaysLoad -> { Unit }
            is ForecastUiModel.OnForecastFiveDaysLoadError -> { Unit }
        }
    }

    private fun onWeatherLoad(currentWeather: WeatherResponse) {
        viewModel.getTodayForecast(currentWeather, requireContext())
    }

    private fun onForecastLoad(weathers: List<WeatherResponse>, currentWeather: WeatherResponse) {
        recyclerAdapter.clear()

        val adapter = GroupAdapter<GroupieViewHolder>()
        currentWeather.let {
            val feelsLike = getString(R.string.feels_like, it.main.feelsLike.toString())
            adapter.add(ItemWeatherHeader(it.cityName ?: "City name", it.wind.deg.degreesToCardinal(), it.wind.speed.toString(), feelsLike, "${it.main.tempMax.toInt()}º" + "/" + "${it.main.tempMin.toInt()}º"))
        }
        weathers.let {
            adapter.addAll(it.map { weather ->
                ItemTemperatureHour(
                    hour = weather.date?.let { date -> getHourAmPm(date, DATE_TIME) } ?: "1:00PM",
                    temperature = "${weather.main.temp.toInt()}º",
                    icon = 1
                )
            })
            showLoading(false)
        }
        binding.apply {
            recyclerForecast.adapter = adapter
            swipeHour.isRefreshing = false
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
}