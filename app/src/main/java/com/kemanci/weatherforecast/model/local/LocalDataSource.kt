package com.kemanci.weatherforecast.model.local

import com.kemanci.weatherforecast.model.local.SharedPrefManager.Companion.APIKEY
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val sharedPrefManager: SharedPrefManager) {

    fun saveApiKey(key: String){
        sharedPrefManager.saveString(APIKEY,key)
    }

    fun getApiKey():String?{
        return sharedPrefManager.getString(APIKEY)
    }

    fun saveString(key: String, data: String) {
        sharedPrefManager.saveString(key, data)
    }

    fun getString(key: String): String? = sharedPrefManager.getString(key)

    fun saveInt(key: String, data: Int) {
        sharedPrefManager.saveInt(key, data)
    }

    fun getInt(key: String): Int = sharedPrefManager.getInt(key)

    fun saveBoolean(key: String, data: Boolean) {
        sharedPrefManager.saveBoolean(key, data)
    }

    fun getBoolean(key: String): Boolean = sharedPrefManager.getBoolean(key)
}