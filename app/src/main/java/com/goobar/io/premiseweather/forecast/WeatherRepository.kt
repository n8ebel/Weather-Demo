package com.goobar.io.premiseweather.forecast

import kotlinx.coroutines.flow.Flow

data class ForecastData(val valid_date: String, val temp: Float)
data class Forecast(val country_code: String, val city_name: String, val data: List<ForecastData>)

interface WeatherRepository {
    val currentForecast: Flow<Forecast>

    suspend fun loadForecast(zipcode: String)
}