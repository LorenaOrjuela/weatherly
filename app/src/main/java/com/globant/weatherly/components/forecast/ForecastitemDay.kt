package com.globant.weatherly.components.forecast

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.globant.weatherly.R
import androidx.compose.material.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.globant.weatherly.components.common.RemoteImage
import com.globant.weatherly.models.ForecastDay

@Composable
fun ForecastDayItem(forecastDay: ForecastDay) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.spacing_xxx_small))
            .background(colorResource(R.color.primary_color)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.high_temp, forecastDay.maxTemp),
                style = MaterialTheme.typography.body1,
                fontSize = 15.sp
            )
            Text(
                text = stringResource(R.string.low_temp, forecastDay.minTemp),
                style = MaterialTheme.typography.body1,
                fontSize = 15.sp
            )
        }
        Column (
            modifier = Modifier.weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = forecastDay.date,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = stringResource(R.string.wind_speed),
                style = MaterialTheme.typography.body2
            )
            Text(
                text = stringResource(R.string.speed, forecastDay.speed),
                style = MaterialTheme.typography.body2
            )
            Text(
                text = forecastDay.direction,
                style = MaterialTheme.typography.body2
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RemoteImage(
                //TODO: Handle https://openweathermap.org/img/wn/${iconCode}@2x.png by implementing a centralized way.
                "https://openweathermap.org/img/wn/${forecastDay.iconCode}@2x.png",
                        modifier = Modifier.size(dimensionResource(R.dimen.spacing_medium))
            )
            Text(
                text = forecastDay.description,
                style = MaterialTheme.typography.body2
            )
        }
    }
}