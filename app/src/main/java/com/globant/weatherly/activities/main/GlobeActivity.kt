package com.globant.weatherly.activities.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.globant.weatherly.R
import com.globant.weatherly.databinding.ActivityGlobeBinding
import com.globant.weatherly.fragments.forecast.ForecastFragment
import com.globant.weatherly.fragments.forecast.HomeFragment

class GlobeActivity: AppCompatActivity() {

    private lateinit var binding: ActivityGlobeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGlobeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViews()
    }

    private fun setViews() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> { replaceFragment(HomeFragment()) }
                R.id.forecast -> { replaceFragment(ForecastFragment()) }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}