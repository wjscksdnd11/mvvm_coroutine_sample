package com.jeon.listsample.ui.wheather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeon.listsample.data.dto.DayWeatherItem
import com.jeon.listsample.databinding.ItemWeatherBinding

class WeatherViewHolder(private val binding: ItemWeatherBinding,val viewModel: WeatherViewModel) :RecyclerView.ViewHolder(binding.root){


    companion object {
        fun createViewHolder(parent: ViewGroup,viewModel: WeatherViewModel): WeatherViewHolder{
            val binding = ItemWeatherBinding.
                inflate(LayoutInflater.from(parent.context), parent, false)
            return WeatherViewHolder(binding,viewModel)
        }
    }
    fun bind(item: DayWeatherItem){
        binding.item = item
        binding.viewModel = viewModel
    }

}