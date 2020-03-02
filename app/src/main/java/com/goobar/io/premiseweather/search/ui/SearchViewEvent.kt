package com.goobar.io.premiseweather.search.ui

sealed class SearchViewEvent {
    object ShowForecast : SearchViewEvent()
    object ShowInvalidInput : SearchViewEvent()
}