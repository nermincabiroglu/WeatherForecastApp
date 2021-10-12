package com.kemanci.weatherforecast.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DailyWeatherReport(
    @SerializedName("dt")
    var dt: Long,

    @SerializedName("sunrise")
    var sunrise: Long,

    @SerializedName("sunset")
    var sunset: Long,

    @SerializedName("moonrise")
    var moonrise: Long,

    @SerializedName("moonset")
    var moonset: Long,

    @SerializedName("moon_phase")
    var moonPhase: Double,

    @SerializedName("temp")
    var temp: Temperature,

    @SerializedName("feels_like")
    var feelsLike: FeelsLikeTemperature,

    @SerializedName("pressure")
    var pressure: Long,

    @SerializedName("humidity")
    var humidity: Long,

    @SerializedName("dew_point")
    var dewPoint: Double,

    @SerializedName("wind_speed")
    var windSpeed: Double,

    @SerializedName("wind_deg")
    var windDeg: Long,

    @SerializedName("wind_gust")
    var windGust: Double,

    @SerializedName("weather")
    var weather: List<Weather>,

    @SerializedName("clouds")
    var clouds: Long,

    @SerializedName("pop")
    var pop: Double,

    @SerializedName("rain")
    var rain: Double,

    @SerializedName("uvi")
    var uvi: Double
):Parcelable