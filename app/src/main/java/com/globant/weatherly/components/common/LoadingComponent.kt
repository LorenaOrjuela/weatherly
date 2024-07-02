package com.globant.weatherly.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.globant.weatherly.R

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(dimensionResource(id = R.dimen.spacing_x_small)), // Assuming you have some padding, adjust as necessary
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                color = colorResource(id = R.color.primary_color),
                modifier = Modifier.size(dimensionResource(id = R.dimen.loading_diameter))
            )
            Text(
                text = stringResource(id = R.string.loading),
                color = Color.Black,
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.spacing_small)),
                textAlign = TextAlign.Center
            )
        }
    }
}