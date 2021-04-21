package com.example.swapbook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.swapbook.firebase.FirebaseService
import com.example.swapbook.helpers.UserAdapter
import com.example.swapbook.home.HomeFragment
import com.example.swapbook.model.ChatStatus
import com.example.swapbook.model.User
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_users_list.*
import kotlinx.android.synthetic.main.activity_users_list.bottom_navigation


class UsersListActivity : AppCompatActivity() {
    var userList = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)

        val bottomNavigationView = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        bottomNavigationView.selectedItemId = R.id.chatFragment

        bottom_navigation.setOnNavigationItemSelectedListener {
                item ->
            when(item.itemId) {
                R.id.homeFragment -> {
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.searchBarFragment -> {
                    val intent = Intent(this,SearchActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.insertionFragment -> {
                    val intent = Intent(this, InsertionActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.chatFragment -> {
                    val intent = Intent(this, UsersListActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.activitiesFragment -> {
//                    val intent = Intent(this,HomeFragment::class.java)
//                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
//        FirebaseService.sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
//        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
//            FirebaseService.token = it.token
//        }

        userRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

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
        userList.clear()
        val firebase: FirebaseUser = FirebaseAuth.getInstance().currentUser!!

        var userid = firebase.uid
        FirebaseMessaging.getInstance().subscribeToTopic("/topics/$userid")

        val databaseReference: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Users")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                val currentUser = snapshot.getValue(User::class.java)
                if (currentUser!!.profileImage == "") {
                    imgProfile.setImageResource(R.drawable.profile_image)
                } else {
                    Glide.with(this@UsersListActivity).load(currentUser.profileImage).into(
                        imgProfile
                    )
                }
                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val user = dataSnapShot.getValue(User::class.java)
                    if (!user!!.userId.equals(firebase.uid)) {

                        val databaseReference2: DatabaseReference =
                            FirebaseDatabase.getInstance().getReference("ChatsStatus")

                        databaseReference2.addValueEventListener(object : ValueEventListener {
                            override fun onCancelled(error: DatabaseError) {
                                Toast.makeText(
                                    applicationContext,
                                    error.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            override fun onDataChange(snapshot: DataSnapshot) {
                                var chatted = false
                                for (dataSnapShot2: DataSnapshot in snapshot.children) {
                                    val chatstatus = dataSnapShot2.getValue(ChatStatus::class.java)
                                    if (chatstatus != null) {
                                        if (((chatstatus.receiverId == user!!.userId) && (chatstatus.senderId == userid)) ||
                                            ((chatstatus.receiverId == userid) && (chatstatus.senderId == user!!.userId))
                                        ) {
                                            userList.add(user)
                                        }
                                    }
                                }
                                val userAdapter = UserAdapter(this@UsersListActivity, userList)
                                userRecyclerView.adapter = userAdapter

                            }
                        })
                    }
                }
            }
        })
    }
}