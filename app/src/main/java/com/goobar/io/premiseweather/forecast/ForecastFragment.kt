package com.goobar.io.premiseweather.forecast

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.goobar.io.premiseweather.databinding.ForecastFragmentBinding

class ForecastFragment : Fragment() {

    private lateinit var viewModel: ForecastViewModel
    private lateinit var viewBinding: ForecastFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = ForecastFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ForecastViewModel::class.java)

        viewBinding.locationButton.setOnClickListener {
            val action = ForecastFragmentDirections.actionForecastFragmentToLocationFragment()
            findNavController().navigate(action)
        }
    }
}
