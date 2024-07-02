package com.globant.weatherly.components.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.globant.weatherly.R
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest


@Composable
fun RemoteImage(url: String, modifier: Modifier = Modifier) {
    val painter =    rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .placeholder(R.drawable.ic_weather_placeholder)
            .error(R.drawable.ic_weather_placeholder)
            .build(),
        contentScale = ContentScale.Fit
    )
    Image(
        painter = painter,
        contentDescription = "Loaded Image",
        modifier = modifier
    )
}