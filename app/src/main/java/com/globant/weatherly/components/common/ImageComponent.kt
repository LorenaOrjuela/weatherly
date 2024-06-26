package com.globant.weatherly.components.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.globant.weatherly.R
import androidx.compose.foundation.Image


@Composable
fun RemoteImage(url: String, modifier: Modifier = Modifier) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            scale(Scale.FIT)
            crossfade(true)
            placeholder(R.drawable.ic_weather_placeholder)
            error(R.drawable.ic_weather_placeholder)
        }
    )
    Image(
        painter = painter,
        contentDescription = "Loaded Image",
        modifier = modifier
    )
}