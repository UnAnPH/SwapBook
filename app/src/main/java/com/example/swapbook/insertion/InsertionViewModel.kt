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



    }
}