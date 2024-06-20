package com.globant.weatherly.fragments.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.globant.weatherly.databinding.FragmentForecastBinding
import com.globant.weatherly.databinding.FragmentHomeBinding
import com.globant.weatherly.databinding.ItemForecastDayBinding
import com.globant.weatherly.models.ForecastDay
import com.globant.weatherly.models.WeatherResponse
import com.globant.weatherly.uimodels.forecast.ForecastUiModel
import com.globant.weatherly.uimodels.weather.WeatherUiModel
import com.globant.weatherly.utils.DATE_TIME
import com.globant.weatherly.utils.getHourAmPm
import com.globant.weatherly.viewmodels.forecast.ForecastViewModel
import com.globant.weatherly.viewmodels.home.HomeViewModel
import com.globant.weatherly.views.forecast.ItemsForecastDay
import com.globant.weatherly.views.home.ItemTemperatureHour
import com.globant.weatherly.views.home.ItemWeatherHeader
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastFragment: Fragment() {

    private val viewModel: ForecastViewModel by viewModels()
    private lateinit var binding: FragmentForecastBinding
    private val recyclerAdapter by lazy { GroupAdapter<GroupieViewHolder>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeToViewModel()
        viewModel.getFiveDaysForecast(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setListeners()
    }

    private fun subscribeToViewModel() {
        viewModel.getForecastUiModels().observe( this@ForecastFragment, Observer { handleForecastUpdate(it) } )
        viewModel.getShowLoading().observe(this@ForecastFragment, Observer { showLoading(it) })
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
        binding.swipeDay.apply {
            setOnRefreshListener {
                isRefreshing = true
                viewModel.getFiveDaysForecast(requireContext())
            }
        }
    }

    private fun handleForecastUpdate(uiModel: ForecastUiModel) {
        when (uiModel) {
            is ForecastUiModel.OnForeCastFiveDaysLoad -> { onForecastLoad(uiModel.forecasts) }
            is ForecastUiModel.OnForecastFiveDaysLoadError -> {  }
            is ForecastUiModel.OnForecastLoad -> {  }
            is ForecastUiModel.OnForecastLoadError-> {  }
        }
    }

    private fun onForecastLoad(forecasts: List<ForecastDay>) {
        recyclerAdapter.clear()

        recyclerAdapter.addAll(
            forecasts.map { forecast ->
                ItemsForecastDay(
                    date = forecast.date,
                    hiTemp = forecast.maxTemp,
                    lowTemp = forecast.minTemp,
                    speed = forecast.speed,
                    direction = forecast.direction,
                    description = forecast.description,
                    icon = 1
                )
            }
        )
        binding.swipeDay.isRefreshing = false
    }

    private fun showLoading(show: Boolean) {
        binding.root.apply {
            visibility = if (show) View.VISIBLE else View.GONE
        }
    }
}