package com.goobar.io.premiseweather.forecast

sealed class ForecastViewAction {
    object Refresh : ForecastViewAction()
}