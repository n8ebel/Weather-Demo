package com.goobar.io.premiseweather.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.goobar.io.premiseweather.R
import com.goobar.io.premiseweather.databinding.ForecastFragmentBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
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
            model.viewState.collect{ onViewState(it)}
        }
    }

    private fun onViewState(viewState: ForecastViewState) {

        viewBinding.progress.isVisible = viewState.isLoading
        if (viewState.isLoading) {
            viewBinding.text1.text = getString(R.string.forecast_loading)
        }

        activity?.title = viewState.forecast?.city_name ?: getString(R.string.app_name)

        viewState.error?.let {
            viewBinding.text1.text = getString(R.string.forecast_error_loading)
        }

        if (!viewState.isLoading && viewState.forecast == null && viewState.error == null) {
            viewBinding.text1.text = getString(R.string.forecast_empty)
        }
    }
}
