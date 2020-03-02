package com.goobar.io.premiseweather.search.ui

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.goobar.io.premiseweather.exhaustive
import com.goobar.io.premiseweather.location.Location
import com.goobar.io.premiseweather.location.LocationRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.asFlow

@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModel(val repository: LocationRepository) : ViewModel() {

    private val _viewEvents = BroadcastChannel<SearchViewEvent>(BUFFERED)
    val viewEvents = _viewEvents.asFlow()

    fun onAction(viewAction: SearchViewAction) {
        when (viewAction) {
            is SearchViewAction.ShowForecastClicked -> {
                if (viewAction.input.length < 5 || !viewAction.input.isDigitsOnly()) {
                    _viewEvents.offer(SearchViewEvent.ShowInvalidInput)
                } else {
                    repository.saveLocation(Location.fromString(viewAction.input))
                    _viewEvents.offer(SearchViewEvent.ShowForecast)
                }
            }
        }.exhaustive
    }
}
