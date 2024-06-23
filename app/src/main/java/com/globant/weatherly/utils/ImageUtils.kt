package com.globant.weatherly.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object ImageUtils {

    fun loadImageResized(imageView: ImageView, uri: String) {

        Glide
            .with(imageView.context)
            .load(uri).apply(RequestOptions.overrideOf(100, 100))
            .into(imageView)
    }
}