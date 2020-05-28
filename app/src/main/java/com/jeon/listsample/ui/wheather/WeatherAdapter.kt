package com.jeon.listsample.ui.wheather

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager

import com.jeon.listsample.data.dao.Weather
import com.jeon.listsample.data.dto.DayWeatherItem

class WeatherAdapter(val viewModel: WeatherViewModel) :
    ListAdapter<DayWeatherItem, WeatherViewHolder>(object :
        DiffUtil.ItemCallback<DayWeatherItem>() {
        override fun areItemsTheSame(oldItem: DayWeatherItem, newItem: DayWeatherItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DayWeatherItem, newItem: DayWeatherItem): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder.createViewHolder(parent,viewModel)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}