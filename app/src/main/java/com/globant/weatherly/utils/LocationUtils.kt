package com.globant.weatherly.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.globant.weatherly.models.Location
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await

object LocationUtils {

    suspend fun getLocation (context: Context): Location {
        val locationClient = LocationServices.getFusedLocationProviderClient(context)

        return if (locationPermissionsGranted(context)) {
            try {
                val location = locationClient.lastLocation.await()
                location?.let {
                    Location(
                        it.latitude,
                        it.longitude
                    )
                } ?: defaultLocation()
            } catch (e: SecurityException) {
                defaultLocation()
            }
        } else defaultLocation()
    }

    private fun defaultLocation() = Location(
        latitude = 0.0,
        longitude = 0.0
    )

    private fun locationPermissionsGranted(context: Context): Boolean {

        val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
        var granted = true

        permissions.forEach { permission ->
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                granted = false
                return@forEach
            }
        }

        return granted
    }
}