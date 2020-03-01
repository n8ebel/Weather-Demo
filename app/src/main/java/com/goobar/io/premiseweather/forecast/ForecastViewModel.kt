package com.goobar.io.premiseweather.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goobar.io.premiseweather.location.LocationRepository
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ForecastViewModel(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _viewState = ConflatedBroadcastChannel<String>()
    val viewState = _viewState.asFlow()

    init {
        viewModelScope.launch {
            locationRepository.currentLocation.collect { location ->
                _viewState.offer(location.zipcode)

                weatherRepository.loadForecast(location.zipcode)
            }
        }

        viewModelScope.launch {
            weatherRepository.currentForecast.collect {
                _viewState.offer("loaded the forecast")
            }
        }
    }
}
