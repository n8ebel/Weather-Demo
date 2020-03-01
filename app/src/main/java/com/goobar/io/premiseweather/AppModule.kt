package com.goobar.io.premiseweather

import android.content.Context
import com.goobar.io.premiseweather.forecast.ForecastViewModel
import com.goobar.io.premiseweather.location.LocationRepository
import com.goobar.io.premiseweather.location.PrefsLocationRepository
import com.goobar.io.premiseweather.search.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    single<LocationRepository> {
        PrefsLocationRepository(androidContext().getSharedPreferences("location", Context.MODE_PRIVATE))
    }
}