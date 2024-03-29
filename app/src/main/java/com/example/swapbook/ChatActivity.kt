package com.example.swapbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.swapbook.helpers.ChatAdapter
import com.example.swapbook.model.Chat
import com.example.swapbook.model.ChatStatus
//import com.example.swapbook.model.NotificationData
//import com.example.swapbook.model.PushNotification
import com.example.swapbook.model.User
import com.example.swapbook.network.RetrofitInstance
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.ArrayList

class ChatActivity : AppCompatActivity() {
    var firebaseUser: FirebaseUser? = null
    var reference: DatabaseReference? = null
    var chatList = ArrayList<Chat>()
//    var topic = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        chatRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)


        var intent = getIntent()
        var userId = intent.getStringExtra("userId")
        var userName = intent.getStringExtra("userName")

        imgBack.setOnClickListener {
            onBackPressed()
        }

        firebaseUser = FirebaseAuth.getInstance().currentUser
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userId!!)

        reference!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                val user = snapshot.getValue(User::class.java)
                tvUserName.text = user!!.userName
                if (user.profileImage == "") {
                    imgProfile.setImageResource(R.drawable.profile_image)
                } else {
                    Glide.with(this@ChatActivity).load(user.profileImage).into(imgProfile)
                }
            }
        })
        btnSendMessage.setOnClickListener {
            var message: String = etMessage.text.toString()

            if (message.isEmpty()) {
                Toast.makeText(applicationContext, "message is empty", Toast.LENGTH_SHORT).show()
                etMessage.setText("")
            } else {
                sendMessage(firebaseUser!!.uid, userId, message)
                etMessage.setText("")
//                topic = "/topics/$userId"
//                PushNotification(
//                    NotificationData(userName!!, message),
//                    topic
//                ).also {
//                    sendNotification(it)
//                }

            }

        }
        readMessage(firebaseUser!!.uid, userId)
    }



    private fun sendMessage(senderId: String, receiverId: String, message: String) {
        var reference: DatabaseReference? = FirebaseDatabase.getInstance().getReference()

        var hashMap: HashMap<String, String> = HashMap()
        hashMap.put("senderId", senderId)
        hashMap.put("receiverId", receiverId)
        hashMap.put("message", message)

        reference!!.child("Chat").push().setValue(hashMap)

        val databaseReference: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("ChatsStatus")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var chatted = false
                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val chatstatus = dataSnapShot.getValue(ChatStatus::class.java)
                    chatstatus?.let { Log.i("chatstatus receiverid:", it.receiverId) }
                    chatstatus?.let { Log.i("chatstatus senderId:", it.senderId) }
                    Log.i("chat receiverid:", receiverId)
                    Log.i("chat senderId:", senderId)
                    if (chatstatus != null) {
                        if (((chatstatus.receiverId == receiverId) && (chatstatus.senderId == senderId))) {
                            chatted = true
                        }
                    }
                }
                Log.i("chatted:", chatted.toString())
                if(!chatted) {
                    var hashMap2: HashMap<String, String> = HashMap()
                    hashMap2.put("senderId", senderId)
                    hashMap2.put("receiverId", receiverId)
                    hashMap2.put("status", "1")

                    reference!!.child("ChatsStatus").push().setValue(hashMap2)
                }
            }

        })



    }
    fun readMessage(senderId: String, receiverId: String) {
        val databaseReference: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Chat")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                chatList.clear()
                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val chat = dataSnapShot.getValue(Chat::class.java)

                    if (chat!!.senderId.equals(senderId) && chat!!.receiverId.equals(receiverId) ||
                        chat!!.senderId.equals(receiverId) && chat!!.receiverId.equals(senderId)
                    ) {
                        chatList.add(chat)
                    }
                }

                val chatAdapter = ChatAdapter(this@ChatActivity, chatList)

                chatRecyclerView.adapter = chatAdapter
            }
        })
    }

//    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
//        try {
//            val response = RetrofitInstance.api.postNotification(notification)
//            if(response.isSuccessful) {
//                Log.d("TAG", "Response: ${Gson().toJson(response)}")
//            } else {
//                Log.e("TAG", response.errorBody()!!.string())
//            }
//        } catch(e: Exception) {
//            Log.e("TAG", e.toString())
//        }
//    }


}