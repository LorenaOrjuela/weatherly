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
import com.globant.weatherly.R
import com.globant.weatherly.databinding.FragmentForecastBinding
import com.globant.weatherly.models.ForecastDay
import com.globant.weatherly.uimodels.forecast.ForecastUiModel
import com.globant.weatherly.viewmodels.forecast.ForecastViewModel
import com.globant.weatherly.views.forecast.ItemsForecastDay
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastFragment: Fragment() {

    private val viewModel: ForecastViewModel by viewModels()
    private var nullableBinding: FragmentForecastBinding? = null
    private val binding get() = nullableBinding!!
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
        nullableBinding = FragmentForecastBinding.inflate(inflater, container, false)
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
            is ForecastUiModel.OnForecastFiveDaysLoadError -> {
                showLoading(false)
                showError(true)
            }
            is ForecastUiModel.OnForecastLoad -> { Unit }
            is ForecastUiModel.OnForecastLoadError-> { Unit }
        }
    }

    private fun onForecastLoad(forecasts: List<ForecastDay>) {
        recyclerAdapter.clear()

        recyclerAdapter.addAll(
            forecasts.map { forecast ->
                ItemsForecastDay(
                    date = forecast.date,
                    hiTemp = getString(R.string.high_temp, forecast.maxTemp),
                    lowTemp = getString(R.string.low_temp, forecast.minTemp),
                    speed = "${forecast.speed} mph",
                    direction = forecast.direction,
                    description = forecast.description,
                    iconCode = forecast.iconCode
                )
            }
        )
        showLoading(false)
        binding.swipeDay.isRefreshing = false
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