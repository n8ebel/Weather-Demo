package com.goobar.io.premiseweather.forecast.ui

import com.goobar.io.premiseweather.data.Forecast

data class ForecastViewState(
    val isLoading: Boolean = true,
    val forecast: Forecast? = null,
    val error: Throwable? = null
)
