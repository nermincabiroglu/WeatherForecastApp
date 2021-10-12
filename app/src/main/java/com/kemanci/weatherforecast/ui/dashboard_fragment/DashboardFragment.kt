package com.kemanci.weatherforecast.ui.dashboard_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.kemanci.weatherforecast.R
import com.kemanci.weatherforecast.databinding.DashboardFragmentBinding
import com.kemanci.weatherforecast.model.entity.*
import com.kemanci.weatherforecast.utils.Constants
import com.kemanci.weatherforecast.utils.Resource.Status.*
import dagger.hilt.android.AndroidEntryPoint
import kotlin.collections.ArrayList


@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private lateinit var binding:DashboardFragmentBinding
    private val dailyWeatherReportList: ArrayList<DailyWeatherReport> = ArrayList()
    private lateinit var weatherRecyclerViewAdapter: WeatherRecyclerViewAdapter

    private val viewModel:DashboardViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DashboardFragmentBinding.inflate(inflater,container,false)
        weatherRecyclerViewAdapter = WeatherRecyclerViewAdapter(
            dailyWeatherReportList = dailyWeatherReportList,
            context = requireContext()
        )
        binding.weatherRecyclerView.adapter = weatherRecyclerViewAdapter
        binding.weatherRecyclerView.layoutManager = LinearLayoutManager(context)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getWeatherData(41.0151,28.9795).observe(viewLifecycleOwner,{
            when(it.status){
                SUCCESS -> {
                    binding.loadingProgressbar.visibility = View.GONE
                    weatherRecyclerViewAdapter.addAllItems(it.data!!.daily)
                    setCurrentTemperatureContainer(it.data)
                }
                ERROR -> {
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                    binding.loadingProgressbar.visibility = View.GONE
                }
                LOADING -> {
                    binding.loadingProgressbar.visibility = View.VISIBLE
                }
            }
        })
        super.onViewCreated(view, savedInstanceState)
    }


    fun setCurrentTemperatureContainer(data:WeatherReport){
        binding.currentWeatherTempTextView.text = data.current.temp.toInt().toString().plus("˚")
        binding.locationTextView.text = data.timezone.replace("/",", ")
        val url:String = Constants.baseUrlForAssets.plus(data.current.weather.first().icon_url).plus("@4x.png")
        Glide.with(binding.currentWeatherImageView.context).load(url).placeholder(
            R.drawable.img_err).into(binding.currentWeatherImageView)
    }
}
