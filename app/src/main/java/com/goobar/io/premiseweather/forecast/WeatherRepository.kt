package com.goobar.io.premiseweather.forecast

import kotlinx.coroutines.flow.Flow

class Forecast

interface WeatherRepository {
    val currentForecast: Flow<Forecast>

    suspend fun loadForecast(zipcode: String)
}