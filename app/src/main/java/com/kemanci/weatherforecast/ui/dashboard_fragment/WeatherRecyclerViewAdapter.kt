package com.kemanci.weatherforecast.ui.dashboard_fragment

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kemanci.weatherforecast.databinding.WeatherRecyclerViewItemBinding
import com.kemanci.weatherforecast.model.entity.DailyWeatherReport
import com.kemanci.weatherforecast.utils.TimeUtil


class WeatherRecyclerViewAdapter(
    private var dailyWeatherReportList: ArrayList<DailyWeatherReport>,
    private val context: Context): RecyclerView.Adapter<WeatherRecyclerViewAdapter.WeatherViewHolder>() {


    inner class WeatherViewHolder(private val binding: WeatherRecyclerViewItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:DailyWeatherReport){
            binding.itemDayTextview.text =  TimeUtil.timestamptoDayName(item.dt)
            binding.itemDaytimeTextview.text = item.temp.day.toInt().toString().plus("˚")
            binding.itemNightTextview.text = item.temp.night.toInt().toString().plus("˚")
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(item:DailyWeatherReport){
        dailyWeatherReportList.add(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding: WeatherRecyclerViewItemBinding =WeatherRecyclerViewItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        return holder.bind(dailyWeatherReportList[position])
    }

    override fun getItemCount(): Int {
        return this.dailyWeatherReportList.size
    }
}