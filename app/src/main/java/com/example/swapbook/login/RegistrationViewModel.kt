package com.example.swapbook.login

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RegistrationViewModel(): ViewModel(){
//    private fun registerUser(userName: String, email: String, password: String): Boolean {
//        val task = auth.createUserWithEmailAndPassword(email, password)
//        task.addOnCompleteListener(getActivity(), object:OnCompleteListener<AuthResult>() {
//            fun onComplete(@NonNull task:Task<AuthResult>) {}
//        })
//    }
}