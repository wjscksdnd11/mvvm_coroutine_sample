package com.jeon.listsample

import com.jeon.listsample.api.WeatherApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import kotlin.coroutines.CoroutineContext

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var apiService: WeatherApiService
    @Before
    fun init(){
        apiService = WeatherApiService.getService()
    }


    @Test
    fun api_test(){
        withContext(viewModel+Dispatchers.IO){

        }
        apiService.getWeather()
    }

}
