package com.goobar.io.premiseweather.forecast

data class ForecastViewState(
    val isLoading: Boolean = true,
    val forecast: Forecast? = null,
    val error: Throwable? = null
)
