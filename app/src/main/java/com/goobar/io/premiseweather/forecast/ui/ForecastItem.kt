package com.goobar.io.premiseweather.forecast.ui

import android.view.View
import android.widget.TextView
import com.goobar.io.premiseweather.R
import com.goobar.io.premiseweather.forecast.ForecastData
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder

class ForecastViewHolder(view: View) : ViewHolder(view) {
    val title = view.findViewById<TextView>(R.id.text1)
}

data class ForecastItem(val forecastData: ForecastData) : Item<ForecastViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.item_forecast
    }

    override fun createViewHolder(itemView: View): ForecastViewHolder {
        return ForecastViewHolder(itemView)
    }

    override fun bind(viewHolder: ForecastViewHolder, position: Int) {
        viewHolder.title.text = forecastData.valid_date
    }

    override fun getId(): Long {
        return forecastData.ts
    }
}