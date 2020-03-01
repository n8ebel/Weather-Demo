package com.goobar.io.premiseweather.forecast

import kotlinx.coroutines.flow.Flow

data class ForecastData(val valid_date: String, val ts:Long, val temp: Float)
data class Forecast(val country_code: String, val city_name: String, val data: List<ForecastData>)

sealed class WeatherResult {
    object Loading : WeatherResult()
    data class Success(val forecast: Forecast) : WeatherResult()
    data class Error(val error: Throwable) : WeatherResult()
}

interface WeatherRepository {
    val currentForecast: Flow<WeatherResult>

    suspend fun loadForecast(zipcode: String)
}