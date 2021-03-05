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
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.swapbook.MainActivity
import com.example.swapbook.database.BookDatabase


class HomeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: HomeFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.home_fragment, container, false)

        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = BookDatabase.getInstance(application).bookDatabaseDao
        val viewModelFactory = HomeViewModelFactory(dataSource, application)

        val homeViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(HomeViewModel::class.java)

        binding.homeViewModel= homeViewModel

        binding.lifecycleOwner = this

        val navHostFragment =
                getActivity()?.getSupportFragmentManager()?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.setLifecycleOwner(this)

        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            homeViewModel.displayPostDetails(it)
        })

        homeViewModel.navigateToSelectedPost.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToBookDetailFragment(it))
                homeViewModel.displayPostDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }



}