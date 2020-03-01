package com.goobar.io.premiseweather

import android.content.Context
import com.goobar.io.premiseweather.location.LocationRepository
import com.goobar.io.premiseweather.location.PrefsLocationRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val AppModule = module {
    single<LocationRepository> {
        PrefsLocationRepository(
            androidContext().getSharedPreferences(
                "location",
                Context.MODE_PRIVATE
            )
        )
    }
}