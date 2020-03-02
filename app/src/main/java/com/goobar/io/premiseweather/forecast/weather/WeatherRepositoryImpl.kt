package com.goobar.io.premiseweather.forecast.weather

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

@ExperimentalCoroutinesApi
@FlowPreview
class WeatherRepositoryImpl(private val weatherService: WeatherService) :
    WeatherRepository {

    private val _currentForecast = ConflatedBroadcastChannel<WeatherResult>()

    override val currentForecast: Flow<WeatherResult> = _currentForecast.asFlow()

    // todo get new key and replace this one with build property
    // todo supply key at some higher level of configuration rather than specific calls
    override suspend fun loadForecast(zipcode: String) {
        _currentForecast.offer(WeatherResult.Loading)
        try {
            val forecast = weatherService
                .get16DayForecast("18d17c88b3c14f90bd34f2175afb73be", zipcode)
            _currentForecast.offer(WeatherResult.Success(forecast))
        } catch (error: Throwable) {
            _currentForecast.offer(WeatherResult.Error(error))
        }
    }
}