package com.globant.weatherly.views.home

import com.globant.weatherly.R
import com.globant.weatherly.databinding.ItemWatherHeaderBinding
import com.xwray.groupie.databinding.BindableItem

class ItemWeatherHeader(
    private val city: String,
    private val direction: String,
    private val speed: String,
    private val feelsLike: String,
    private val maxMinTemperature: String): BindableItem<ItemWatherHeaderBinding>() {

    override fun getLayout() = R.layout.item_wather_header

    override fun bind(viewBinding: ItemWatherHeaderBinding, position: Int) {

        viewBinding.apply {
            textViewCity.text = city
            textViewDirection.text = direction
            textViewSpeed.text = speed
            textViewFeelsLike.text = feelsLike
            textViewMinMax.text = maxMinTemperature
        }
    }
}