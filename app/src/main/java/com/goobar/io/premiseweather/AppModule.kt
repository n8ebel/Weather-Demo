package com.goobar.io.premiseweather

import android.content.Context
import com.goobar.io.premiseweather.forecast.WeatherRepository
import com.goobar.io.premiseweather.forecast.WeatherRepositoryImpl
import com.goobar.io.premiseweather.forecast.WeatherService
import com.goobar.io.premiseweather.location.LocationRepository
import com.goobar.io.premiseweather.location.PrefsLocationRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val AppModule = module {
    single<LocationRepository> {
        PrefsLocationRepository(
            androidContext().getSharedPreferences(
                "location",
                Context.MODE_PRIVATE
            )
        )
    }

    single<WeatherService> {
        val builder = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.weatherbit.io/v2.0/")
            .build()
        builder.create(WeatherService::class.java)
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(get())
    }
}