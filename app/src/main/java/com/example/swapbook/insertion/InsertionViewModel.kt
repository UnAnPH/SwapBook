package com.example.swapbook.insertion

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.viewinterop.viewModel
import androidx.lifecycle.*
import com.example.swapbook.MainActivity
import com.example.swapbook.R
import com.example.swapbook.databinding.InsertionFragmentBinding
import com.example.swapbook.helpers.Utils
import com.example.swapbook.home.MarsApiStatus
import com.example.swapbook.network.Post
import com.example.swapbook.network.ResponseModel
import com.example.swapbook.network.SwapBookApi
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.android.play.core.splitinstall.c
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController.getContext

class InsertionViewModel(application: Application): ViewModel(){
    // TODO: Implement the ViewModel

    private val _newPost = MutableLiveData<Post>()
    val newPost: LiveData<Post>
        get() = _newPost

    init {
    }


    fun addPost(binding: InsertionFragmentBinding){
        val bookTitle = binding.editTextBookTitle.text.toString()
        val authorName = binding.editTextAuthorName.text.toString()
        val genre = binding.spinnerGenre.selectedItem.toString()
        val description = binding.editMultiLineDescription.text.toString()
        val publishingYear = binding.editTextPublishingYear.text.toString()
        val publishingHouse = binding.editTextPublishingHouse.text.toString()
        val physicalDescription = binding.editTextPhysicalDesc.text.toString()
        val condition = binding.editTextCondition.text.toString()
        val lenght = binding.editTextLenght.text.toString()
        val height = binding.editTextHeight.text.toString()
        val width = binding.editTextWidth.text.toString()
        val city = binding.editTextCity.text.toString()
        val province = binding.editTextProvince.text.toString()
        val price = binding.editTextPrice.text.toString()


        val insertData: Call<ResponseModel?>? = SwapBookApi.retrofitService.insertData(
                    "INSERT", bookTitle,
                    authorName, genre,
                    description, publishingYear, publishingHouse,physicalDescription,condition,
                    lenght,height,width,city,province,price)

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