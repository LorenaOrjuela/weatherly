package com.globant.weatherly.views.home

import com.globant.weatherly.R
import com.globant.weatherly.databinding.ItemForecastHourBinding
import com.xwray.groupie.databinding.BindableItem

class ItemTemperatureHour(
    private val hour: String,
    private val temperature: String,
    private val icon: Int): BindableItem<ItemForecastHourBinding>() {

    override fun getLayout() = R.layout.item_forecast_hour

    override fun bind(viewBinding: ItemForecastHourBinding, position: Int) {

        viewBinding.apply {
            textViewHour.text = hour
            textViewTemperature.text = temperature
        }
    }
}