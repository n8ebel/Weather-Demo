package com.goobar.io.premiseweather.forecast.ui

sealed class ForecastViewAction {
    object Refresh : ForecastViewAction()
}