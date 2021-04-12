package com.example.swapbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.swapbook.home.HomeFragment
import com.example.swapbook.network.ResponseModel
import com.example.swapbook.network.SwapBookApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_insertion.*
import kotlinx.android.synthetic.main.activity_users_list.*
import kotlinx.android.synthetic.main.activity_users_list.bottom_navigation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        val bottomNavigationView = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        bottomNavigationView.selectedItemId = R.id.insertionFragment

        bottom_navigation.setOnNavigationItemSelectedListener {
                item ->
            when(item.itemId) {
                R.id.homeFragment -> {
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.searchBarFragment -> {
//                    val intent = Intent(this,HomeFragment::class.java)
//                    startActivity(intent)
                    true
                }
                R.id.insertionFragment -> {
                    true
                }
                R.id.chatFragment -> {
                    val intent = Intent(this,UsersListActivity::class.java)
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

        buttonAddPost.setOnClickListener {
           addPost()
        }
    }

    private fun addPost() {
        val bookTitle = editTextBookTitle.text.toString()
        val authorName = editTextAuthorName.text.toString()
        val genre = spinnerGenre.selectedItem.toString()
        val description = editMultiLineDescription.text.toString()
        val publishingYear = editTextPublishingYear.text.toString()
        val publishingHouse = editTextPublishingHouse.text.toString()
        val physicalDescription = editTextPhysicalDesc.text.toString()
        val condition = editTextCondition.text.toString()
        val lenght = editTextLenght.text.toString()
        val height = editTextHeight.text.toString()
        val width = editTextWidth.text.toString()
        val city = editTextCity.text.toString()
        val province = editTextProvince.text.toString()
        val price = editTextPrice.text.toString()


        val insertData: Call<ResponseModel?>? = SwapBookApi.retrofitService.insertData(
            "INSERT", bookTitle,
            authorName,
            genre,
            description, publishingYear, publishingHouse,physicalDescription,condition,
            lenght,height,width,city,province,price
        )

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