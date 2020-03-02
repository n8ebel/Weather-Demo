package com.goobar.io.premiseweather.search

import com.goobar.io.premiseweather.search.ui.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val SearchModule = module {
    viewModel { SearchViewModel(get()) }
}