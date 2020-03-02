package com.goobar.io.premiseweather.location

import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    val currentLocation: Flow<Location>

    fun saveLocation(location: Location)
}