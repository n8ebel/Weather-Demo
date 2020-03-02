package com.goobar.io.premiseweather

import android.content.Context
import com.goobar.io.premiseweather.data.ForecastData
import com.goobar.io.premiseweather.forecast.weather.WeatherRepository
import com.goobar.io.premiseweather.forecast.weather.WeatherRepositoryImpl
import com.goobar.io.premiseweather.forecast.weather.WeatherService
import com.goobar.io.premiseweather.location.LocationRepository
import com.goobar.io.premiseweather.location.PrefsLocationRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
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
        val type = Types.newParameterizedType(
            MutableList::class.java,
            ForecastData::class.java
        )
        val moshi = Moshi.Builder().build()
        moshi.adapter<List<ForecastData>>(type)

        val builder = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://api.weatherbit.io/v2.0/")
            .build()
        builder.create(WeatherService::class.java)
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(get())
    }
}