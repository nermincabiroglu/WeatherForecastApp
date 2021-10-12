package com.kemanci.weatherforecast.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherReport (
    @SerializedName("lat")
    var lat: Double,

    @SerializedName("lon")
    var lon: Double,

    @SerializedName("timezone")
    var timezone: String,

    @SerializedName("timezone_offset")
    var timezoneOffset: Long,

    @SerializedName("current")
    var current: CurrentWeatherReport,

    @SerializedName("daily")
    var daily: List<DailyWeatherReport>

):Parcelable