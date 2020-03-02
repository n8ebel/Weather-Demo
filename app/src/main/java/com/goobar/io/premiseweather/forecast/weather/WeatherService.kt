package com.goobar.io.premiseweather.forecast.weather

import com.goobar.io.premiseweather.data.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast/daily")
    suspend fun get16DayForecast(
        @Query("key") key: String,
        @Query("postal_code") zipcode: String
    ): Forecast
}