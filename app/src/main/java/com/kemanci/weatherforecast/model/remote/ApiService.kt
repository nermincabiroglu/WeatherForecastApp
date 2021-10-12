package com.kemanci.weatherforecast.model.remote

import com.kemanci.weatherforecast.model.entity.WeatherReport
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("/data/2.5/onecall")
    suspend fun getWeatherData(
        @Query("lat") latitude:Double,
        @Query("lon") longitude:Double,
        @Query("exclude") exclude:String,
        @Query("units") units:String,
        @Query("appid") appId:String
    ):Response<WeatherReport>
}