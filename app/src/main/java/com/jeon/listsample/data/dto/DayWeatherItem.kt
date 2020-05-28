package com.jeon.listsample.data.dto

data class DayWeatherItem(
    val clouds: Int,
    val dewPoint: Double,
    val dt: Int,
    var feelsLike: Double?=null,
    val humidity: Int,
    val pressure: Int,
    val sunrise: Int,
    val sunset: Int,
    val iconId:String?,
    val uvi: Double,
    var visibility: Int?=null,
    val windDeg: Int,
    val windSpeed: Double,
    val date:String,
    val week:String,
    val description:String?,
    val max:String,
    val min:String
)


