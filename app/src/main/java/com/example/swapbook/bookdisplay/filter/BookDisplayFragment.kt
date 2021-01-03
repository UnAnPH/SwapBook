package com.example.swapbook.bookdisplay.filter

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swapbook.R

class BookDisplayFragment : Fragment() {

    companion object {
        fun newInstance() = BookDisplayFragment()
    }

    private lateinit var viewModel: BookDisplayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.book_display_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookDisplayViewModel::class.java)
        // TODO: Use the ViewModel
    }

}