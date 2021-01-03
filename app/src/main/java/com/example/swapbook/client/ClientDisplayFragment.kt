package com.example.swapbook.client

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swapbook.R

class ClientDisplayFragment : Fragment() {

    companion object {
        fun newInstance() = ClientDisplayFragment()
    }

    private lateinit var viewModel: ClientDisplayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.client_display_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ClientDisplayViewModel::class.java)
        // TODO: Use the ViewModel
    }

}