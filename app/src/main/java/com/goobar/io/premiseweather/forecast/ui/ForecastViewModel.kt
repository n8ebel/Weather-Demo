package com.goobar.io.premiseweather.forecast.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goobar.io.premiseweather.exhaustive
import com.goobar.io.premiseweather.forecast.weather.WeatherRepository
import com.goobar.io.premiseweather.forecast.weather.WeatherResult
import com.goobar.io.premiseweather.location.LocationRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class ForecastViewModel(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _viewState = ConflatedBroadcastChannel<ForecastViewState>()
    val viewState = _viewState.asFlow()

    init {
        _viewState.offer(
            ForecastViewState(
                isLoading = false
            )
        )

        loadCurrentLocation()

        viewModelScope.launch {
            weatherRepository.currentForecast.collect {
                val currentViewState = _viewState.valueOrNull ?: ForecastViewState()

                val viewState = when (it) {
                    WeatherResult.Loading -> currentViewState.copy(isLoading = true)
                    is WeatherResult.Success -> {
                        ForecastViewState(
                            isLoading = false,
                            forecast = it.forecast,
                            error = null
                        )
                    }
                    is WeatherResult.Error -> currentViewState.copy(
                        isLoading = false,
                        error = it.error
                    )
                }
                _viewState.offer(viewState)
            }
        }
    }

    fun onAction(action: ForecastViewAction) {
        when (action) {
            ForecastViewAction.Refresh -> loadCurrentLocation()
        }.exhaustive
    }

    private fun loadCurrentLocation() {
        viewModelScope.launch {
            locationRepository.currentLocation.collect { location ->

                weatherRepository.loadForecast(location.zipcode)
            }
        }
    }
}
