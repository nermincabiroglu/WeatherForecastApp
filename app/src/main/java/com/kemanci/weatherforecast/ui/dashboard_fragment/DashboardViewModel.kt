package com.kemanci.weatherforecast.ui.dashboard_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kemanci.weatherforecast.model.ApiRepository
import com.kemanci.weatherforecast.model.entity.WeatherReport
import com.kemanci.weatherforecast.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



@HiltViewModel
class DashboardViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {
    private var apiKey: String? = apiRepository.getApiKey()
    fun getApiKey() = this.apiKey

    fun setApiKey(key:String){
        apiRepository.saveApiKey(key)
    }

    fun getWeatherData(latitude:Double,longitude:Double):LiveData<Resource<WeatherReport>>{
        return apiRepository.getWeatherData(latitude,longitude)
    }
}