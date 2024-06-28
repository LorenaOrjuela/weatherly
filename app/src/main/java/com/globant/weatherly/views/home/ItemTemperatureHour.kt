package com.globant.weatherly.views.home

import com.globant.weatherly.R
import com.globant.weatherly.databinding.ItemForecastHourBinding
import com.globant.weatherly.utils.DATE_TIME
import com.globant.weatherly.utils.ImageUtils
import com.globant.weatherly.utils.getHourAmPm
import com.xwray.groupie.databinding.BindableItem

class ItemTemperatureHour(
    private val date: String?,
    private val temperature: Double,
    private val iconCode: String): BindableItem<ItemForecastHourBinding>() {

    override fun getLayout() = R.layout.item_forecast_hour

    override fun bind(viewBinding: ItemForecastHourBinding, position: Int) {

        viewBinding.apply {
            textViewHour.text = date?.let { date -> getHourAmPm(date, DATE_TIME) } ?: "1:00PM"
            textViewTemperature.text = "${temperature.toInt()}º"
            //TODO: Handle https://openweathermap.org/img/wn/${iconCode}@2x.png by implementing a centralized way.
            ImageUtils.loadImageResized(imageViewWeather, "https://openweathermap.org/img/wn/${iconCode}@2x.png")
        }
    }
}