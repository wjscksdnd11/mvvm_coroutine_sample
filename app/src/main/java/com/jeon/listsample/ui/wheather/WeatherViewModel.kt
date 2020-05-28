package com.jeon.listsample.ui.wheather

import android.view.View
import androidx.lifecycle.*
import com.jeon.listsample.data.dao.Weather
import com.jeon.listsample.data.dto.DayWeatherItem
import com.jeon.listsample.data.dto.TodayWeather
import com.jeon.listsample.repository.WeatherRepo
import kotlinx.coroutines.Dispatchers

class WeatherViewModel(private val weatherRepo: WeatherRepo) : ViewModel() {

    private val _loading = MutableLiveData<Int>()
    val loading = _loading
    val headerData :MutableLiveData<TodayWeather?>  = MutableLiveData()
    val weatherList: LiveData<List<DayWeatherItem>?> = liveData(Dispatchers.IO) {
        showLoading()
        emit(weatherRepo.getWeekWeathers())
        headerData.postValue(weatherRepo.getCurrentWeather(false))
        hideLoading()
    }
    val detailWeather = MutableLiveData<DayWeatherItem>()

    private fun showLoading() {
        _loading.postValue(View.VISIBLE)
    }

    private fun hideLoading() {
        _loading.postValue(View.GONE)
    }

    fun itemClick(position:Int){
        weatherList.value?.let {
            detailWeather.value = it[position]
        }
    }

}