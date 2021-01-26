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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.swapbook.MainActivity
import com.example.swapbook.bookdisplay.BookDisplayFragment
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

//        val adapter = BookDisplayAdapter()

//        binding.bookCarouselHome1.adapter=adapter
//
//        homeViewModel.books.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                adapter.submitList(it)
//            }
//        })

        binding.setLifecycleOwner(this)

        binding.button.setOnClickListener {
            (activity as MainActivity).makeCurrentFragment(BookDisplayFragment())
        }

//        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
//            when(item.itemId) {
//                R.id.search_bar -> {
//                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchBarFragment())
//                    true
//                }


//        val manager = GridLayoutManager(activity, 3, GridLayoutManager.HORIZONTAL, false)
//
//        binding.bookCarouselHome1.layoutManager= manager

        return binding.root
    }



}