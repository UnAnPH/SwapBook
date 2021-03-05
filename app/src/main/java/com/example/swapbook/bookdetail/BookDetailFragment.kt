package com.example.swapbook.bookdetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swapbook.R
import com.example.swapbook.databinding.BookDetailFragmentBinding

class BookDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = BookDetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val post = BookDetailFragmentArgs.fromBundle(arguments!!).selectedPost
        val viewModelFactory = BookDetailViewModelFactory(post, application)
        binding.viewModel = ViewModelProvider(
                this, viewModelFactory).get(BookDetailViewModel::class.java)

        return binding.root
    }
}