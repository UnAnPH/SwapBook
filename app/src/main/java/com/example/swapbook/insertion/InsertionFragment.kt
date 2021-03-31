package com.example.swapbook.insertion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.swapbook.R
import com.example.swapbook.bookdetail.BookDetailViewModel
import com.example.swapbook.bookdetail.BookDetailViewModelFactory
import com.example.swapbook.databinding.InsertionFragmentBinding
import com.example.swapbook.home.HomeViewModel
import com.example.swapbook.home.HomeViewModelFactory
import com.example.swapbook.searchbar.SearchBarViewModel

class InsertionFragment : Fragment() {


    private lateinit var binding: InsertionFragmentBinding

    private lateinit var viewModel: InsertionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = InsertionFragmentBinding.inflate(inflater)

        val application = requireNotNull(activity).application

        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        val viewModelFactory = InsertionViewModelFactory(application)
//        binding.insertionViewModel = ViewModelProvider(
//                this, viewModelFactory).get(InsertionViewModel::class.java)

        val insertionViewModel =
                ViewModelProvider(
                        this, viewModelFactory).get(InsertionViewModel::class.java)

        binding.buttonAddPost.setOnClickListener {
            insertionViewModel.addPost(binding)

        }

    return binding.root
    }
}