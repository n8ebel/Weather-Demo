package com.goobar.io.premiseweather.location

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.goobar.io.premiseweather.databinding.LocationFragmentBinding

class LocationFragment : Fragment() {

    private lateinit var viewBinding: LocationFragmentBinding
    private lateinit var viewModel: LocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = LocationFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)

        viewBinding.searchButton.setOnClickListener {
            val action = LocationFragmentDirections.actionLocationFragmentToForecastFragment()
            findNavController().navigate(action)
        }
    }
}
