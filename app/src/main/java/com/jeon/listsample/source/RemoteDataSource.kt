package com.jeon.listsample.source

import com.jeon.listsample.api.WeatherApiService
import com.jeon.listsample.data.dao.WeatherInfo


class RemoteDataSource(
    private val apiService: WeatherApiService
) : DataSource {

    override suspend fun getWeather(lat: String,lon:String): WeatherInfo {
       return  apiService.getWeather(lat,lon)
    }

}