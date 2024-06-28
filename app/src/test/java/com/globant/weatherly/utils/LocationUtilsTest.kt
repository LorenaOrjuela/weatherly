package com.globant.weatherly.utils


import android.content.Context
import android.location.Location
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.globant.weatherly.TestConstants
import com.globant.weatherly.utils.LocationUtils.getLocation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
class LocationUtilsTest {

    //TODO is generating a error with mock, review and fix it
    @Test
    fun `get current location when location permissions are granted and location is available`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val locationClient = mockk<FusedLocationProviderClient>()
        val anyLocation = Location(TestConstants.GPS_PROVIDER).apply {
            latitude = 7.00
            longitude = -75.00
        }

        every { LocationUtils.locationPermissionsGranted(context) } returns true
        every { LocationServices.getFusedLocationProviderClient(context) } returns locationClient
        coEvery { locationClient.lastLocation.await() } returns anyLocation

        val location = runBlocking { getLocation(context) }

        assertEquals(7.00, location.latitude)
        assertEquals(-75.00, location.longitude)
    }
}