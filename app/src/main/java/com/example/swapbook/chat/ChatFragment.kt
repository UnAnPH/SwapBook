package com.example.swapbook.chat

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.swapbook.R
import com.example.swapbook.databinding.ChatFragmentBinding
import com.example.swapbook.databinding.SearchBarFragmentBinding
import com.example.swapbook.insertion.InsertionViewModel

class ChatFragment : Fragment() {

    private lateinit var binding: ChatFragmentBinding

    private lateinit var viewModel: ChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.chat_fragment, container, false)

        viewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        binding.chatViewModel= viewModel

        return binding.root
    }


}