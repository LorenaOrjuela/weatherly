package com.globant.weatherly.fragments.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.globant.weatherly.models.ForecastDay
import com.globant.weatherly.uimodels.forecast.ForecastUiModel
import com.globant.weatherly.viewmodels.forecast.ForecastViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.globant.weatherly.components.common.ErrorScreen
import com.globant.weatherly.components.common.LoadingScreen
import com.globant.weatherly.components.forecast.ForecastDayItem

@AndroidEntryPoint
class ForecastFragment: Fragment() {

    private val viewModel: ForecastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getFiveDaysForecast()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            ForecastScreen(viewModel)
        }
    }

    @Composable
    fun ForecastScreen(viewModel: ForecastViewModel) {
        val uiModel = viewModel.getForecastUiModels().observeAsState().value
        when (uiModel) {
            is ForecastUiModel.OnForecastLoading -> LoadingScreen()
            is ForecastUiModel.OnForeCastFiveDaysLoad -> ForecastList(uiModel.forecasts)
            is ForecastUiModel.OnForecastFiveDaysLoadError -> ErrorScreen()
            else -> { Unit }
        }
    }

    @Composable
    fun ForecastList(forecasts: List<ForecastDay>) {
        LazyColumn {
            items(forecasts) { forecast ->
                ForecastDayItem(forecast)
            }
        }
    }
}