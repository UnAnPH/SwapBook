package com.example.swapbook.insertion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.swapbook.R
import com.example.swapbook.databinding.InsertionFragmentBinding
import com.example.swapbook.searchbar.SearchBarViewModel

class InsertionFragment : Fragment() {


    private lateinit var binding: InsertionFragmentBinding

    private lateinit var viewModel: InsertionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.insertion_fragment, container, false)

        viewModel = ViewModelProvider(this).get(InsertionViewModel::class.java)

        binding.insertionViewModel= viewModel

    return binding.root
    }
}