package com.kemanci.weatherforecast.ui.dashboard_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kemanci.weatherforecast.databinding.DashboardFragmentBinding
import com.kemanci.weatherforecast.model.entity.DailyWeatherReport
import com.kemanci.weatherforecast.model.entity.FeelsLikeTemperature
import com.kemanci.weatherforecast.model.entity.Temperature
import com.kemanci.weatherforecast.model.entity.Weather
import com.kemanci.weatherforecast.utils.TimeUtil
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {
    private lateinit var binding:DashboardFragmentBinding
    private val dailyWeatherReportList: ArrayList<DailyWeatherReport> = ArrayList()
    private lateinit var weatherRecyclerViewAdapter: WeatherRecyclerViewAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DashboardFragmentBinding.inflate(inflater,container,false)
        weatherRecyclerViewAdapter = WeatherRecyclerViewAdapter(
            dailyWeatherReportList = dailyWeatherReportList,
            context = requireContext()
        )

        val fakeW = Weather(id=0,main = "Rain",description = "orta şiddetli yağmur",icon_url = "10d")
        val fakeData:DailyWeatherReport = DailyWeatherReport(
            dt = 1634029200,
            sunrise = 1634011921,
            sunset = 1634052526,
            moonrise = 1634036880,
            moonset = 1634069220,
            moonPhase = 0.22,
            temp = Temperature(day=21.2, min=17.03, max=21.2, night=17.03, eve=19.25, morn=17.72),
            feelsLike = FeelsLikeTemperature(day=20.99 , night=16.96 , eve=19.24 , morn=17.66),
            pressure = 1008,
            humidity = 62,
            dewPoint = 13.64,
            windSpeed = 7.82,
            windDeg = 187,
            windGust = 0.0,
            weather = listOf(fakeW),
            clouds = 0,
            pop = 1,
            rain = 0.0,
            uvi = 1.1
        )
        weatherRecyclerViewAdapter.addItem(fakeData)
        weatherRecyclerViewAdapter.addItem(fakeData)
        weatherRecyclerViewAdapter.addItem(fakeData)
        binding.weatherRecyclerView.adapter = weatherRecyclerViewAdapter
        binding.weatherRecyclerView.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }
}
