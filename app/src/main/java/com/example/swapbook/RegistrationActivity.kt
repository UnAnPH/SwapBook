package com.example.swapbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.swapbook.network.ResponseModel
import com.example.swapbook.network.SwapBookApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registration.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        auth = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
            val userName = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (TextUtils.isEmpty(userName)){
                Toast.makeText(applicationContext,"username is required", Toast.LENGTH_SHORT).show()
            }
            if (TextUtils.isEmpty(email)){
                Toast.makeText(applicationContext,"email is required", Toast.LENGTH_SHORT).show()
            }

            if (TextUtils.isEmpty(password)){
                Toast.makeText(applicationContext,"password is required", Toast.LENGTH_SHORT).show()
            }

            if (TextUtils.isEmpty(confirmPassword)){
                Toast.makeText(applicationContext,"confirm password is required", Toast.LENGTH_SHORT).show()
            }

            if (!password.equals(confirmPassword)){
                Toast.makeText(applicationContext,"password not match", Toast.LENGTH_SHORT).show()
            }
            registerUser(userName,email,password)

        }

        btnLogin.setOnClickListener {
            val intent = Intent(this@RegistrationActivity,
                LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun registerUser(userName:String,email:String,password:String) {
    var userIDInt:String = ""
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val user: FirebaseUser? = auth.currentUser
                    val userId: String = user!!.uid
                    userIDInt = userId
                    databaseReference =
                        FirebaseDatabase.getInstance().getReference("Users").child(userId)
                    val hashMap: HashMap<String, String> = HashMap()
                    hashMap.put("userId", userId)
                    hashMap.put("userName", userName)
                    hashMap.put("profileImage", "")

                    databaseReference.setValue(hashMap).addOnCompleteListener(this) {
                        if (it.isSuccessful) {
//                            open home activity
                            etName.setText("")
                            etEmail.setText("")
                            etPassword.setText("")
                            etConfirmPassword.setText("")
                            val intent = Intent(
                                this@RegistrationActivity,
                                MainActivity::class.java
                            )
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }

        var firebaseUser = FirebaseAuth.getInstance().currentUser

        val insertData1: Call<ResponseModel?>? = SwapBookApi.retrofitService.insertUser(
            "INSERT_USER", ""
        )

        val insertData: Call<ResponseModel?>? = SwapBookApi.retrofitService.insertUser(
            "INSERT_USER", firebaseUser.uid
        )

        Log.i("msgreg", firebaseUser.uid)

        insertData!!.enqueue(object : Callback<ResponseModel?> {
            override fun onResponse(
                call: Call<ResponseModel?>?,
                response: Response<ResponseModel?>?
            ) {

                var myResponseCode = ""
                if (response?.body() != null) {
                    myResponseCode = response.body()!!.code!!
                }
                when {
                    myResponseCode == "1" -> {
                        Log.i( "msg:","""SUCCESS:
                 1. Data Inserted Successfully.
                 2. ResponseCode: $myResponseCode"""
                        )
                    }
                    myResponseCode.equals("2", ignoreCase = true) -> {
                        Log.i( "msg:", "UNSUCCESSFUL However Good Response. 1. CONNECTION TO SERVER WAS SUCCESSFUL 2. WE ATTEMPTED POSTING DATA BUT ENCOUNTERED ResponseCode: $myResponseCode 3. Most probably the problem is with your PHP Code."                        )
                    }
                    myResponseCode.equals("3", ignoreCase = true) -> {
                        Log.i( "msg:", "NO MYSQL CONNECTION Your PHP Code is unable to connect to mysql database. Make sure you have supplied correct database credentials." )
                    }
                }
            }

            override fun onFailure(call: Call<ResponseModel?>?, t: Throwable) {
                Log.i( "msg:", "FAILURE FAILURE THROWN DURING INSERT." + " ERROR Message: " + t.message)
            }
        })
    }
}