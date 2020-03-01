package com.goobar.io.premiseweather.forecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

import com.goobar.io.premiseweather.databinding.ForecastFragmentBinding
import com.goobar.io.premiseweather.location.Location
import kotlinx.coroutines.flow.collect

class ForecastFragment : Fragment() {

    private val model: ForecastViewModel by viewModel()
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

        viewBinding.locationButton.setOnClickListener {
            val action = ForecastFragmentDirections.actionForecastFragmentToSearchFragment()
            findNavController().navigate(action)
        }

        lifecycleScope.launchWhenStarted {
            model.viewState.collect{
                viewBinding.textView.text = "Showing location data for $it"
            }
        }
    }
}
