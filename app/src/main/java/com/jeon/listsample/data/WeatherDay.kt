package com.jeon.listsample.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * {
 * "id":429009,
 * "weather_state_name":"Clear",
 * "weather_state_abbr":"c",
 * "wind_direction_compass":"NW",
 * "created":"2013-04-30T22:55:17.582290Z",
 * "applicable_date":"2013-04-30",
 * "min_temp":null,"max_temp":null,
 * "the_temp":27.67,"wind_speed":9.2608902,
 * "wind_direction":315.0,
 * "air_pressure":1012.0,
 * "humidity":37,
 * "visibility":18.191263023940188,
 * "predictability":68
 * }
 */
data class WeatherDay(
    @SerializedName("air_pressure")
    val airPressure: Double,
    val applicable_date: String,
    val created: String,
    val humidity: Int,
    val id: Int,
    @SerializedName("max_temp")
    val maxTemp: Any,
    @SerializedName("min_temp")
    val minTemp: Any,
    val predictability: Int,
    @SerializedName("the_temp")
    val theTemp: Double,
    val visibility: Double,
    @SerializedName("weather_state_abbr")
    val weatherStateAbbr: String,
    @SerializedName("weather_state_name")
    val weatherStateName: String,
    @SerializedName("wind_direction")
    val windDirection: Double,
    @SerializedName("wind_direction_compass")
    val windDirectionCompass: String,
    @SerializedName("wind_speed")
    val windSpeed: Double
):Serializable