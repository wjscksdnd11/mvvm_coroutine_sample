package com.jeon.listsample.source

import com.jeon.listsample.api.WeatherApiService
import com.jeon.listsample.data.dao.WeatherInfo
import java.util.*


class RemoteDataSource(
    private val apiService: WeatherApiService
) : DataSource {

    override suspend fun getWeather(lat: String,lon:String): WeatherInfo {
       return  apiService.getWeather(lat,lon)
    }

}