package com.kemanci.weatherforecast.ui.home_fragment

import androidx.lifecycle.ViewModel
import com.kemanci.weatherforecast.model.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val  apiRepository: ApiRepository): ViewModel(){
    private var apiKey: String? = apiRepository.getApiKey()
    fun getApiKey() = this.apiKey

    fun setApiKey(key:String){
        apiRepository.saveApiKey(key)
    }
}