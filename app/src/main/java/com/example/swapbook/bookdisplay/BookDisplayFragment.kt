package com.example.swapbook.bookdisplay

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.swapbook.R
import com.example.swapbook.client.ClientDisplayFragment
import com.example.swapbook.databinding.BookDisplayFragmentBinding
import com.example.swapbook.databinding.GridViewItemBinding



class BookDisplayFragment : Fragment() {

    private val viewModel: BookDisplayViewModel by lazy {
        ViewModelProvider(this).get(BookDisplayViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = BookDisplayFragmentBinding.inflate(inflater)


        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
//        binding.lifecycleOwner = this
        binding.setLifecycleOwner(this)

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        binding.photosGrid.adapter = PhotoGridAdapter()



        setHasOptionsMenu(true)
        return binding.root
    }

}