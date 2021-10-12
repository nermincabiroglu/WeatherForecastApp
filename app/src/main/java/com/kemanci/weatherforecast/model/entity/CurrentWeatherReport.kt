package com.kemanci.weatherforecast.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class CurrentWeatherReport(
    @SerializedName("dt")
    var dt: Long,

    @SerializedName("sunrise")
    var sunrise: Long,

    @SerializedName("sunset")
    var sunset: Long,

    @SerializedName("temp")
    var temp: Double,

    @SerializedName("feels_like")
    var feelsLike: Double,

    @SerializedName("pressure")
    var pressure: Long,

    @SerializedName("humidity")
    var humidity: Long,

    @SerializedName("dew_point")
    var dewPoint: Double,

    @SerializedName("uvi")
    var uvi: Double,

    @SerializedName("clouds")
    var clouds: Long,

    @SerializedName("visibility")
    var visibility: Long,

    @SerializedName("wind_speed")
    var windSpeed: Double,

    @SerializedName("wind_deg")
    var windDeg: Long,

    @SerializedName("weather")
    var weather: List<Weather>

):Parcelable