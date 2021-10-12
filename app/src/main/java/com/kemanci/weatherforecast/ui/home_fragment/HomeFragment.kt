package com.kemanci.weatherforecast.ui.home_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kemanci.weatherforecast.databinding.HomeFragmentBinding
import com.kemanci.weatherforecast.utils.KeyboardHelper
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding:HomeFragmentBinding
    private val viewModel:HomeViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = HomeFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.enterButton.setOnClickListener {
           val text:String =  binding.textInputLayout.editText?.text.toString()
           if(text.isNullOrEmpty()){
               binding.textInputLayout.error = "Api key boş olamaz"
               return@setOnClickListener
           }
           binding.textInputLayout.error = ""
           getView()?.let { it1 -> KeyboardHelper.hideKeyboard(requireActivity(), it1) }
           viewModel.setApiKey(text)
           Log.e("TAG", "onViewCreated:$text")
           findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDashboardFragment())
        }
        super.onViewCreated(view, savedInstanceState)
    }


}