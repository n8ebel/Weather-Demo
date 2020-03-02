package com.goobar.io.premiseweather.location

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

private val KEY_LOCATION = "key_location"

@ExperimentalCoroutinesApi
@FlowPreview
class PrefsLocationRepository(val prefs: SharedPreferences) : LocationRepository {

    private val _currentLocation = ConflatedBroadcastChannel<Location>()

    override val currentLocation: Flow<Location> = _currentLocation.asFlow()

    init {
        prefs.getString(KEY_LOCATION, null)?.let { savedLocation ->
            _currentLocation.offer(Location.fromString(savedLocation))
        }
    }

    override fun saveLocation(location: Location) {
        prefs.edit {
            putString(KEY_LOCATION, location.toString())
        }
        _currentLocation.offer(location)
    }
}