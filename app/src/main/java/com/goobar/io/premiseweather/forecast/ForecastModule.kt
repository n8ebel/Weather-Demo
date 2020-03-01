package com.goobar.io.premiseweather.forecast

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ForecastModule = module {
    viewModel { ForecastViewModel(get(), get()) }
}