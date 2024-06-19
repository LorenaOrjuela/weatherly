package com.globant.weatherly.services.di

import com.globant.weatherly.services.IWeatherController
import com.globant.weatherly.services.IWeatherRepository
import com.globant.weatherly.services.implementation.WeatherController
import com.globant.weatherly.services.implementation.WeatherRepository
import com.globant.weatherly.services.WeatherServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Provides
    fun providesWeatherRepository(weatherServices: WeatherServices): IWeatherRepository {
        return WeatherRepository(weatherServices)
    }

    @Provides
    fun provideWeatherController(weatherRepository: WeatherRepository): IWeatherController {
        return WeatherController(weatherRepository)
    }
}