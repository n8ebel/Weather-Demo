package com.goobar.io.premiseweather.data

data class ForecastData(
    val valid_date: String,
    val ts: Long,
    val temp: Float,
    val pres: Float,
    val rh: Float,
    val pop: Float,
    val weather: Weather
)