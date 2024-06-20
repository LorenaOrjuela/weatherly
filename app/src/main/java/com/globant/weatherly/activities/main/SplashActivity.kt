package com.globant.weatherly.activities.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        goToGlobeActivity()
    }

    private fun goToGlobeActivity() {
        startActivity(Intent(this, GlobeActivity::class.java))
        finish()
    }
}