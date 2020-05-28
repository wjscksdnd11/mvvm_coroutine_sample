package com.jeon.listsample.ui.wheather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jeon.listsample.data.dto.DayWeatherItem
import com.jeon.listsample.data.dto.TodayWeather
import com.jeon.listsample.repository.WeatherRepo
import kotlinx.coroutines.Dispatchers

class WeatherViewModel(private val weatherRepo: WeatherRepo) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>(true)
    val loading:LiveData<Boolean> = _loading
    val headerData: MutableLiveData<TodayWeather?> = MutableLiveData()
    val weatherList: LiveData<List<DayWeatherItem>?> = liveData(Dispatchers.IO) {
        showLoading()
        emit(weatherRepo.getWeekWeathers())
        headerData.postValue(weatherRepo.getCurrentWeather(false))
        hideLoading()

    }

    val detailWeather = MutableLiveData<DayWeatherItem>()

    private fun showLoading() {
        _loading.postValue(true)
    }

    private fun hideLoading() {
        _loading.postValue(false)
    }


    fun itemClick(position: Int) {
        weatherList.value?.let {
            detailWeather.value = it[position]
        }
    }

}