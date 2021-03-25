package com.example.swapbook.login

import android.R.attr
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.swapbook.MainActivity
import com.example.swapbook.R
import com.example.swapbook.databinding.RegistrationFragmentBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference


class RegistrationFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
//    var main = activity as MainActivity?

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: RegistrationFragmentBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.registration_fragment, container, false
        )

        auth = FirebaseAuth.getInstance()

        binding.lifecycleOwner = this

        val viewModelFactory = RegistrationViewModel()

//        binding.btnLogin.setOnClickListener {
//            val userName = binding.etName.text.toString()
//            val email = binding.etEmail.text.toString()
//            val password = binding.etPassword.text.toString()
//            val confirmPassword = binding.etConfirmPassword.text.toString()
//
//            if (TextUtils.isEmpty(userName)){
//                Toast.makeText(getActivity(), "username is required", Toast.LENGTH_SHORT).show()
//            }
//            if (TextUtils.isEmpty(email)){
//                Toast.makeText(getActivity(), "email is required", Toast.LENGTH_SHORT).show()
//            }
//
//            if (TextUtils.isEmpty(password)){
//                Toast.makeText(getActivity(), "password is required", Toast.LENGTH_SHORT).show()
//            }
//
//            if (TextUtils.isEmpty(confirmPassword)){
//                Toast.makeText(getActivity(), "confirm password is required", Toast.LENGTH_SHORT).show()
//            }
//
//            if (!password.equals(confirmPassword)){
//                Toast.makeText(getActivity(), "password not match", Toast.LENGTH_SHORT).show()
//            }
//
//            if(registerUser(userName, email, password)) {
//                findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToHomeFragment())
//            }else{
//                Toast.makeText(getActivity(), "123pepelaugh", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//
//
//
//    }
//
//    private fun registerUser(userName: String, email: String, password: String): Boolean {
//        val task = auth.createUserWithEmailAndPassword(email, password)
//        task.addOnCompleteListener(getActivity(), object:OnCompleteListener<AuthResult>() {
//            fun onComplete(@NonNull task:Task<AuthResult>) {}
//        })
//    }
//
        return binding.root
    }
}