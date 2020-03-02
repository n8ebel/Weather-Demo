package com.goobar.io.premiseweather.forecast.weather

import com.goobar.io.premiseweather.data.Forecast
import kotlinx.coroutines.flow.Flow

sealed class WeatherResult {
    object Loading : WeatherResult()
    data class Success(val forecast: Forecast) : WeatherResult()
    data class Error(val error: Throwable) : WeatherResult()
}

interface WeatherRepository {
    val currentForecast: Flow<WeatherResult>

    suspend fun loadForecast(zipcode: String)
}