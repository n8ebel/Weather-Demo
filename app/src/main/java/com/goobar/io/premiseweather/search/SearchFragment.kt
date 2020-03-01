package com.goobar.io.premiseweather.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.goobar.io.premiseweather.databinding.SearchFragmentBinding

class SearchFragment : Fragment() {

    private lateinit var viewBinding: SearchFragmentBinding
    private lateinit var viewModel: SearchViewModel

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
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        viewBinding.searchButton.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToForecastFragment()
            findNavController().navigate(action)
        }
    }
}
