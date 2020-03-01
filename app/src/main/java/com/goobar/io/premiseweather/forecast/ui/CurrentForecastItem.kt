package com.goobar.io.premiseweather.forecast.ui

import android.view.View
import android.widget.TextView
import com.goobar.io.premiseweather.R
import com.goobar.io.premiseweather.forecast.ForecastData
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder

class CurrentForecastViewHolder(view: View) : ViewHolder(view) {
    val title = view.findViewById<TextView>(R.id.text1)
}

data class CurrentForecastItem(val forecastData: ForecastData) : Item<CurrentForecastViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.item_forecast_current
    }

    override fun createViewHolder(itemView: View): CurrentForecastViewHolder {
        return CurrentForecastViewHolder(itemView)
    }

    override fun bind(viewHolder: CurrentForecastViewHolder, position: Int) {
        viewHolder.title.text = forecastData.valid_date
    }

    override fun getId(): Long {
        return forecastData.ts
    }

}