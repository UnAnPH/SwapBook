package com.example.swapbook.searchbar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.swapbook.R
import com.example.swapbook.databinding.HomeFragmentBinding
import com.example.swapbook.databinding.SearchBarFragmentBinding
import com.example.swapbook.home.HomeFragmentDirections
import com.example.swapbook.home.HomeViewModel

class SearchBarFragment : Fragment() {

    private lateinit var binding: SearchBarFragmentBinding

    private lateinit var viewModel: SearchBarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(
                inflater, R.layout.search_bar_fragment, container, false)

        viewModel = ViewModelProvider(this).get(SearchBarViewModel::class.java)

        binding.searchBarViewModel= viewModel

        return binding.root
    }

}