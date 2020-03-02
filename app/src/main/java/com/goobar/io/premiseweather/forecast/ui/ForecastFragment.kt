package com.goobar.io.premiseweather.forecast.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.goobar.io.premiseweather.R
import com.goobar.io.premiseweather.databinding.ForecastFragmentBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class ForecastFragment : Fragment() {

    private val model: ForecastViewModel by viewModel()
    private lateinit var viewBinding: ForecastFragmentBinding
    private lateinit var navController: NavController

    private val groupAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = ForecastFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            model.viewState.collect { onViewState(it) }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navController = findNavController()

        viewBinding.recyclerView.apply {
            val manager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(), manager.orientation))
            layoutManager = manager
            adapter = groupAdapter
        }

        viewBinding.refresh.setOnRefreshListener {
            model.onAction(ForecastViewAction.Refresh)
        }

        viewBinding.locationButton.setOnClickListener {
            val action =
                ForecastFragmentDirections.actionForecastFragmentToSearchFragment()
            navController.navigate(action)
        }
    }

    private fun updateProgressIndicator(isLoading: Boolean, hasData: Boolean) {
        if (hasData) {
            viewBinding.refresh.isRefreshing = isLoading
            viewBinding.progress.isVisible = false
        } else {
            viewBinding.progress.isVisible = isLoading
            viewBinding.refresh.isRefreshing = false
        }
    }

    private fun onViewState(viewState: ForecastViewState) {

        updateProgressIndicator(viewState.isLoading, viewState.forecast != null)

        viewBinding.text1.isVisible = viewState.forecast == null

        if (viewState.isLoading) {
            viewBinding.text1.text = getString(R.string.forecast_loading)
        }

        viewState.error?.let {
            viewBinding.text1.text = getString(R.string.forecast_error_loading)
        }

        if (!viewState.isLoading && viewState.forecast == null && viewState.error == null) {
            viewBinding.text1.text = getString(R.string.forecast_empty)
        }

        viewState.forecast?.let { forecast ->
            val items = mutableListOf<Item<*>>()
            items.add(CurrentForecastItem(viewState.forecast.city_name, forecast.data[0]))
            for (i in 1 until forecast.data.size) {
                items.add(ForecastItem(forecast.data[i]))
            }
            groupAdapter.updateAsync(items)
        }
    }
}
