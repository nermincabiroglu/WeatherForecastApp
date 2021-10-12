package com.kemanci.weatherforecast.model

import com.kemanci.weatherforecast.model.local.LocalDataSource
import com.kemanci.weatherforecast.model.remote.RemoteDataSource
import com.kemanci.weatherforecast.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject
    constructor(
    private var remoteDataSource: RemoteDataSource,
    private var localDataSource: LocalDataSource
    ) {

    // TODO: LOCAL DATA PROCESS
    fun saveApiKey(key:String){
        this.localDataSource.saveApiKey(key)
    }

    fun getApiKey():String?{
        return this.localDataSource.getApiKey()
    }


    // TODO: REMOTE DATA PROCESS
    fun getWeatherData(latitude:Double,longitude:Double) = performNetworkOperation {
        remoteDataSource.getWeatherData(latitude,longitude, this.getApiKey().toString())
    }
}