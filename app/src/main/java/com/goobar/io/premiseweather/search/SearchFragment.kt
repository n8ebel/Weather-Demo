package com.goobar.io.premiseweather.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.goobar.io.premiseweather.databinding.SearchFragmentBinding
import com.goobar.io.premiseweather.location.Location
import org.koin.androidx.viewmodel.ext.android.viewModel

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.searchButton.setOnClickListener {
            model.repository.saveLocation(Location.fromString(viewBinding.editText.text.toString()))

            val action = SearchFragmentDirections.actionSearchFragmentToForecastFragment()
            findNavController().navigate(action)
        }
    }
}
