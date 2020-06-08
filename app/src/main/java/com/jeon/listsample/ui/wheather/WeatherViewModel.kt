package com.jeon.listsample.ui.wheather

import androidx.lifecycle.*
import com.jeon.listsample.data.dto.DayWeatherItem
import com.jeon.listsample.data.dto.TodayWeather
import com.jeon.listsample.repository.WeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherRepo: WeatherRepo) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>(true)
    val loading:LiveData<Boolean> = _loading
    val headerData: MutableLiveData<TodayWeather?> = MutableLiveData()
    val errorMessage = MutableLiveData<String>()
    val weatherList :MutableLiveData<List<DayWeatherItem>?> = MutableLiveData()

    fun loadData()= viewModelScope.launch (Dispatchers.IO){
        showLoading()
        try {
            weatherList.postValue(weatherRepo.getWeekWeathers())
            headerData.postValue(weatherRepo.getCurrentWeather(false))
        } catch (e:Exception){
            errorMessage.postValue(e.message)
        }
        hideLoading()
    }

    init {
        loadData()
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