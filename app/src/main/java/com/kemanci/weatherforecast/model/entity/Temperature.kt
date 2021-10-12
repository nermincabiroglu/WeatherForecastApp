package com.kemanci.weatherforecast.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Temperature (
    @SerializedName("day")
    var day: Double,
    @SerializedName("min")
    var min: Double?,
    @SerializedName("max")
    var max: Double?,
    @SerializedName("night")
    var night: Double,
    @SerializedName("eve")
    var eve: Double,
    @SerializedName("morn")
    var morn: Double
):Parcelable
