package com.example.swapbook.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.swapbook.R
import com.example.swapbook.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {


        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View {

            val binding: LoginFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.login_fragment,container,false)

//            binding.loginButton.setOnClickListener {
//                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
//            }

            return binding.root
    }
}