package com.goobar.io.premiseweather.forecast

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

@ExperimentalCoroutinesApi
@FlowPreview
class WeatherRepositoryImpl(private val weatherService: WeatherService) : WeatherRepository {

    private val _currentForecast = ConflatedBroadcastChannel<Forecast>()

    override val currentForecast: Flow<Forecast> = _currentForecast.asFlow()

    override suspend fun loadForecast(zipcode: String) {
        val forecast = weatherService
            .get16DayForecast("18d17c88b3c14f90bd34f2175afb73be", zipcode)
        _currentForecast.offer(forecast)
    }
}