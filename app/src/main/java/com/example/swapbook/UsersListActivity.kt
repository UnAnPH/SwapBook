package com.example.swapbook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.swapbook.helpers.UserAdapter
import com.example.swapbook.model.User
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_users_list.*


class UsersListActivity : AppCompatActivity() {
    var userList = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)


//        userRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//        FirebaseService.sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
//        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
//            FirebaseService.token = it.token
//        }
//
        userRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        imgBack.setOnClickListener {
            onBackPressed()
        }

        imgProfile.setOnClickListener {
            val intent = Intent(
                this@UsersListActivity,
                ProfileActivity::class.java
            )
            startActivity(intent)
        }
        getUsersList()
    }

    fun getUsersList() {
        val firebase: FirebaseUser = FirebaseAuth.getInstance().currentUser!!

        var userid = firebase.uid
//        FirebaseMessaging.getInstance().subscribeToTopic("/topics/$userid")
        Log.i("ciao",FirebaseApp.getInstance().options.toString())

        val databaseReference: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Users")

//        val databaseReference = FirebaseDatabase.getInstance().reference
//        val users = databaseReference.child("users")

        Log.i("msg:", databaseReference.toString())


        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.i("msg:", "fai schifo doppio")
                Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                val currentUser = snapshot.getValue(User::class.java)
                if (currentUser!!.profileImage == "") {
                    imgProfile.setImageResource(R.drawable.profile_image)
                } else {
                    Glide.with(this@UsersListActivity).load(currentUser.profileImage).into(
                        imgProfile
                    )
                }
                Log.i("msg:", "fai schifo0")
                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val user = dataSnapShot.getValue(User::class.java)
                    Log.i("msg:", "fai schifo")
                    if (!user!!.userId.equals(firebase.uid)) {
                        Log.i("msg:", "schifo 2")
                        userList.add(user)
                    }
                }

                val userAdapter = UserAdapter(this@UsersListActivity, userList)

                userRecyclerView.adapter = userAdapter
            }
        })
        Log.i("msg:", "fai schifo3000")
    }
}