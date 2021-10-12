package com.kemanci.weatherforecast.ui.home_fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.*
import com.kemanci.weatherforecast.databinding.HomeFragmentBinding
import com.kemanci.weatherforecast.utils.KeyboardHelper
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var ACCESS_FINE_LOCATION:Boolean = false
    private var ACCESS_COARSE_LOCATION:Boolean = false
    private lateinit var binding: HomeFragmentBinding

    private var currentLocation: Location? = null

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        ACCESS_FINE_LOCATION = ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ACCESS_COARSE_LOCATION = ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
        if(ACCESS_COARSE_LOCATION && ACCESS_FINE_LOCATION) getMyLocation()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.enterButton.setOnClickListener {
            locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
            if (!ACCESS_COARSE_LOCATION or !ACCESS_FINE_LOCATION){
                Toast.makeText(requireContext(),"Uygulamayı kullanmak için\nkonum izinlerini onaylayın",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val text: String = binding.textInputLayout.editText?.text.toString()
            if (text.isNullOrEmpty()) {
                binding.textInputLayout.error = "Api key boş olamaz"
                return@setOnClickListener
            }
            binding.textInputLayout.error = ""
            getView()?.let { it1 -> KeyboardHelper.hideKeyboard(requireActivity(), it1) }
            viewModel.setApiKey(text)
            Log.e("TAG", "onViewCreated:$text")
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDashboardFragment(currentLocation))
        }
        super.onViewCreated(view, savedInstanceState)
    }


    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                ACCESS_FINE_LOCATION = true

            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                ACCESS_COARSE_LOCATION = true
            }
            else -> {
                ACCESS_FINE_LOCATION = false
                ACCESS_COARSE_LOCATION = false
            }
        }
      getMyLocation()
    }


    @SuppressLint("MissingPermission")
    private fun getMyLocation() {


        val mLocationRequest: LocationRequest = LocationRequest.create()
        mLocationRequest.setInterval(60000)
        mLocationRequest.setFastestInterval(5000)
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        val mLocationCallback: LocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    if (location != null) {
                        currentLocation = location
                        Log.e("TAG", "getMyLocation: "+ currentLocation!!.latitude)

                    }
                }
            }
        }
        LocationServices.getFusedLocationProviderClient(requireContext()).requestLocationUpdates(mLocationRequest, mLocationCallback, null)
    }

}