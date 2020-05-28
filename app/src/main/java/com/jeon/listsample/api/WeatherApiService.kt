package com.jeon.listsample.api

import com.jeon.listsample.BuildConfig
import com.jeon.listsample.data.dao.WeatherInfo
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface WeatherApiService {


    @GET("/data/2.5/onecall")
    suspend fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") part:String = BuildConfig.WeatherAPIKey,
        @Query("lang") lang:String = Locale.getDefault().language,
        @Query("units") units:String = "metric"
    ): WeatherInfo

    companion object {
        fun getService() = RequestManager.getRetrofitService(WeatherApiService::class.java)
    }
}