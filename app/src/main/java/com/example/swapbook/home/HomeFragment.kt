package com.example.swapbook.home

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
import com.example.swapbook.databinding.LoginFragmentBinding
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding= DataBindingUtil.inflate(
            inflater, R.layout.home_fragment, container, false)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.homeViewModel= viewModel

//        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
//            when(item.itemId) {
//                R.id.search_bar -> {
//                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchBarFragment())
//                    true
//                }
//                R.id.add -> {
//                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToInsertionFragment())
//                    true
//                }
//                R.id.chat -> {
//                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToChatFragment())
//                    true
//                }
//                R.id.activities -> {
//                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToActivitiesFragment())
//                    true
//                }
//                else -> false
//            }

//        }




        return binding.root
    }



}