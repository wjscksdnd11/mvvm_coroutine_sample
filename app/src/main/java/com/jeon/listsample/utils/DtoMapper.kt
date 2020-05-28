package com.jeon.listsample.utils

import com.jeon.listsample.data.LocationInfo
import com.jeon.listsample.data.dao.Current
import com.jeon.listsample.data.dao.Daily
import com.jeon.listsample.data.dto.DayWeatherItem
import com.jeon.listsample.data.dto.TodayWeather

fun Current.toTodayItem(): TodayWeather {
    return this.let {
        val weather = it.weather.firstOrNull { weather -> weather.icon.isNotEmpty() }
        TodayWeather(
            humidity = it.humidity.toString(),
            iconId = weather?.icon.toString(),
            city =   LocationInfo.address,
            currentTemp = makeTempFormat(it.temp),
            date =getTodayDate(),
            description = weather?.description
        )
    }

}


fun Daily.toDayWeatherItem():DayWeatherItem{
    return this.let {
        val weather = it.weather.firstOrNull { weather -> weather.icon.isNotEmpty() }

        DayWeatherItem(
            clouds = it.clouds,
            dewPoint = it.dew_point,
            dt = it.dt,
            humidity = it.humidity,
            pressure = it.pressure,
            sunrise = it.sunrise,
            sunset = it.sunset,
            uvi = it.uvi,
            iconId = weather?.icon,
            windDeg = it.wind_deg,
            windSpeed = it.wind_speed,
            date = getDate(it.dt),
            description = weather?.description,
            min = makeTempFormat(it.temp.min),
            max = makeTempFormat(it.temp.max),
            week = getWeek(it.dt)
        )
    }
}

private fun  makeTempFormat(temp: Double):String {
    return String.format("%s°C",temp.toInt())
}