package com.goobar.io.premiseweather.forecast.ui

import android.view.View
import android.widget.TextView
import com.goobar.io.premiseweather.R
import com.goobar.io.premiseweather.data.ForecastData
import com.goobar.io.premiseweather.toFarenheit
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder

class ForecastViewHolder(view: View) : ViewHolder(view) {
    val title = view.findViewById<TextView>(R.id.date)
    val temp = view.findViewById<TextView>(R.id.temp)
    val description = view.findViewById<TextView>(R.id.description)
    val details = view.findViewById<TextView>(R.id.details)
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
        viewHolder.description.text = forecastData.weather.description
        viewHolder.temp.text = "${forecastData.temp.toFarenheit()} F"
        viewHolder.details.text = viewHolder.itemView.context.getString(
            R.string.forecast_details,
            forecastData.pop,
            forecastData.rh,
            forecastData.pres
        )
    }

    override fun getId(): Long {
        return forecastData.ts
    }
}