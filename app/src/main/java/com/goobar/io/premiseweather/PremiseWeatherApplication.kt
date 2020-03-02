package com.goobar.io.premiseweather

import android.app.Application
import com.goobar.io.premiseweather.forecast.ForecastModule
import com.goobar.io.premiseweather.search.SearchModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PremiseWeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()

            androidContext(this@PremiseWeatherApplication)

            modules(AppModule, ForecastModule, SearchModule)
        }
    }
}