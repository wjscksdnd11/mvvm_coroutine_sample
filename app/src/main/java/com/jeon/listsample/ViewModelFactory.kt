package com.jeon.listsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jeon.listsample.api.WeatherApiService
import com.jeon.listsample.repository.WeatherRepo
import com.jeon.listsample.source.RemoteDataSource
import com.jeon.listsample.ui.wheather.WeatherViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(WeatherRepo(RemoteDataSource(WeatherApiService.getService()))) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}