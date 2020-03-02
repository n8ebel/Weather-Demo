package com.goobar.io.premiseweather.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.goobar.io.premiseweather.R
import com.goobar.io.premiseweather.databinding.SearchFragmentBinding
import com.goobar.io.premiseweather.exhaustive
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

@FlowPreview
class SearchFragment : Fragment() {

    private lateinit var viewBinding: SearchFragmentBinding
    private val model: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = SearchFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            model.viewEvents.collect { event ->
                when (event) {
                    SearchViewEvent.ShowForecast -> {
                        val action =
                            SearchFragmentDirections.actionSearchFragmentToForecastFragment()
                        findNavController().navigate(action)
                    }
                    SearchViewEvent.ShowInvalidInput -> {
                        Snackbar.make(
                            requireView(),
                            R.string.search_invalid_input,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }.exhaustive
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.searchButton.setOnClickListener {
            model.onAction(
                SearchViewAction.ShowForecastClicked(viewBinding.editText.text.toString())
            )
        }
    }
}
