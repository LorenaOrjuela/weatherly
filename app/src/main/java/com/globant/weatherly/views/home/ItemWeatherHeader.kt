package com.globant.weatherly.views.home

import android.content.Context
import com.globant.weatherly.R
import com.globant.weatherly.databinding.ItemWatherHeaderBinding
import com.globant.weatherly.extensions.degreesToCardinal
import com.xwray.groupie.databinding.BindableItem
import javax.inject.Inject

class ItemWeatherHeader(
    private val context: Context,
    private val city: String?,
    private val direction: Int,
    private val speed: Double,
    private val feelsLike: Double,
    private val maxTemp: Double,
    private val minTemp: Double): BindableItem<ItemWatherHeaderBinding>() {

    override fun getLayout() = R.layout.item_wather_header

    override fun bind(viewBinding: ItemWatherHeaderBinding, position: Int) {

        viewBinding.apply {
            textViewCity.text = city ?: "City name"
            textViewDirection.text = direction.degreesToCardinal()
            textViewSpeed.text = "$speed mph"
            textViewFeelsLike.text = context.getString(R.string.feels_like, feelsLike.toString())
            textViewMinMax.text = "${maxTemp.toInt()}º" + "/" + "${minTemp.toInt()}º"
        }
    }
}