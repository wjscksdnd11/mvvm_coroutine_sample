package com.jeon.listsample.source

import com.jeon.listsample.data.dao.WeatherInfo


interface DataSource{
    suspend fun getWeather(lat:String, lon:String): WeatherInfo
}