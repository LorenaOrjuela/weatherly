package com.globant.weatherly.activities.main

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.globant.weatherly.R
import com.globant.weatherly.databinding.ActivityGlobeBinding
import com.globant.weatherly.fragments.forecast.ForecastFragment
import com.globant.weatherly.fragments.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GlobeActivity: BaseActivity() {

    private lateinit var binding: ActivityGlobeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGlobeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPermissions()
    }

    private fun checkPermissions() {
        if (!hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            requestPermissionsSafely(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_CODE
            )
        } else {
            setViews()
        }
    }

    override fun granted(requestCode: Int) {
        super.granted(requestCode)
        setViews()
    }

    override fun denied(requestCode: Int) {
        super.denied(requestCode)
        Unit
    }

    private fun setViews() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> { replaceFragment(HomeFragment()) }
                R.id.forecast -> { replaceFragment(ForecastFragment()) }
            }
            true
        }
        binding.bottomNavigation.selectedItemId = R.id.home
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}