package com.goobar.io.premiseweather.search.ui

sealed class SearchViewAction {
    data class ShowForecastClicked(val input: String) : SearchViewAction()
}