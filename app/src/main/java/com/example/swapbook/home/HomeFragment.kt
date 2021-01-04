package com.example.swapbook.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.swapbook.R
import com.example.swapbook.databinding.HomeFragmentBinding
import com.example.swapbook.databinding.LoginFragmentBinding

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: LoginFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.home_fragment, container, false)


        return binding.root
    }



}