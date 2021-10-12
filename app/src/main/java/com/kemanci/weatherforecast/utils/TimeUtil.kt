package com.kemanci.weatherforecast.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class TimeUtil {
    companion object{
        fun unixTimeStampToDate(timestamp:Long): Date {
            return Date(timestamp*1000L)
        }
        @SuppressLint("SimpleDateFormat")
        fun dateToDayName(date:Date):String{
            return SimpleDateFormat("EEEE").format(date)
        }

        fun timestamptoDayName(timestamp: Long):String{
            return dateToDayName(unixTimeStampToDate(timestamp))
        }
    }
}