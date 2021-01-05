package com.example.swapbook.activities

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.swapbook.R
import com.example.swapbook.databinding.ActivitiesFragmentBinding
import com.example.swapbook.databinding.SearchBarFragmentBinding
import com.example.swapbook.insertion.InsertionViewModel

class ActivitiesFragment : Fragment() {

    private lateinit var binding: ActivitiesFragmentBinding

    private lateinit var viewModel: ActivitiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.activities_fragment, container, false)

        viewModel = ViewModelProvider(this).get(ActivitiesViewModel::class.java)

        binding.activitiesViewModel= viewModel

        return binding.root
    }

}