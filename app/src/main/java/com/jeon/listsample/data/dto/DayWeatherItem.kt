package com.jeon.listsample.data.dto

data class DayWeatherItem(
    val clouds: Int,
    val dewPoint: Double,
    val dt: Int,
    var feelsLike: String ="",
    val humidity: String,
    val pressure: String,
    val sunrise: Int,
    val sunset: Int,
    val iconId:String?,
    val uvi: Double,
    var visibility: Int?=null,
    val windDeg: Int,
    val windSpeed: String,
    val date:String,
    val week:String,
    val description:String?,
    val max:String,
    val min:String
)


