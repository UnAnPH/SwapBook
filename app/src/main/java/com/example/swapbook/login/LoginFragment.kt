package com.example.swapbook.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.swapbook.MainActivity
import com.example.swapbook.R
import com.example.swapbook.databinding.InsertionFragmentBinding
import com.example.swapbook.databinding.LoginFragmentBinding
import com.example.swapbook.insertion.InsertionViewModel
import com.example.swapbook.insertion.InsertionViewModelFactory

class LoginFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View {

            val binding: LoginFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.login_fragment,container,false)

            val application = requireNotNull(this.activity).application

            binding.lifecycleOwner = this

            val viewModelFactory = LoginViewModelFactory(application)

            val loginViewModel =
                    ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
//
//            binding.loginButton.setOnClickListener {
//                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
//            }
//
//            binding.registrationButton.setOnClickListener {
//                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
//            }

            return binding.root
    }

//    override fun onAttach(context: Context) {
//        (activity as MainActivity)?.hideBottomNavigation()
//        super.onAttach(context)
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        (activity as MainActivity)?.showBottomNavigation()
//    }
}