package com.goobar.io.premiseweather.data

data class Forecast(val country_code: String, val city_name: String, val data: List<ForecastData>)