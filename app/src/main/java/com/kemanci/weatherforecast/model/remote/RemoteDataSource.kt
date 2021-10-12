package com.kemanci.weatherforecast.model.remote

import com.kemanci.weatherforecast.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) : BaseDataSource() {
    suspend fun getWeatherData(latitude:Double,Longitude:Double,apiKey:String) = getResult {
        apiService.getWeatherData(
            latitude = latitude,
            longitude = Longitude,
            appId = apiKey,
            units = "metric",
            exclude = "minutely,hourly"
        )
    }
}