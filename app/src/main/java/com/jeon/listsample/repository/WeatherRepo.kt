package com.jeon.listsample.repository

import com.jeon.listsample.data.LocationInfo
import com.jeon.listsample.data.dao.WeatherInfo
import com.jeon.listsample.data.dto.DayWeatherItem
import com.jeon.listsample.data.dto.TodayWeather
import com.jeon.listsample.source.DataSource
import com.jeon.listsample.utils.toDayWeatherItem
import com.jeon.listsample.utils.toTodayItem

class WeatherRepo( private val remoteSource: DataSource) :
    WeatherRepository {
    private var cacheWeatherInfo: WeatherInfo? = null

    override suspend fun getCurrentWeather(isForceUpdate:Boolean): TodayWeather? {
        if(isForceUpdate) getTotalWeather()
        return cacheWeatherInfo?.current?.toTodayItem()
    }

    override suspend fun getWeekWeathers(isForceUpdate: Boolean): List<DayWeatherItem>? {
        if(isForceUpdate) getTotalWeather()
        return cacheWeatherInfo?.daily?.map{ it.toDayWeatherItem() }
    }

    private suspend fun getTotalWeather() {
        cacheWeatherInfo = remoteSource.getWeather(LocationInfo.latitude, LocationInfo.longitude)
    }

}

interface WeatherRepository {
    suspend fun getCurrentWeather(isForceUpdate:Boolean = true): TodayWeather?
    suspend fun getWeekWeathers(isForceUpdate:Boolean = true): List<DayWeatherItem>?
}
