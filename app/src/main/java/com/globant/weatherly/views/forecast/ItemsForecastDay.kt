package com.globant.weatherly.views.forecast

import com.globant.weatherly.R
import com.globant.weatherly.databinding.ItemForecastDayBinding
import com.xwray.groupie.databinding.BindableItem

class ItemsForecastDay (
    private val date: String,
    private val hiTemp: String,
    private val lowTemp: String,
    private val speed: String,
    private val direction: String,
    private val description: String,
    private val icon: Int): BindableItem<ItemForecastDayBinding>() {

    override fun getLayout() = R.layout.item_forecast_day

    override fun bind(viewBinding: ItemForecastDayBinding, position: Int) {

        viewBinding.apply {
            textViewDate.text = date
            textViewHighTemp.text = hiTemp
            textViewLowTemp.text = lowTemp
            textViewSpeed.text = speed
            textViewDirection.text = direction
            textViewDescription.text = description
        }
    }
}