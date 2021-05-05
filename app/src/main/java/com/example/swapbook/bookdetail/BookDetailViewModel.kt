package com.example.swapbook.bookdetail

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.swapbook.R
import com.example.swapbook.databinding.BookDetailFragmentBinding
import com.example.swapbook.network.MarsProperty
import com.example.swapbook.network.Post
import com.example.swapbook.network.ResponseModel
import com.example.swapbook.network.SwapBookApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookDetailViewModel(post: Post,
                      app: Application) : AndroidViewModel(app) {

    private val _selectedPost = MutableLiveData<Post>()
    val selectedPost: LiveData<Post>
        get() = _selectedPost

    init {
        _selectedPost.value = post
    }

    val displayPostPrice = _selectedPost.value?.price.plus("€")
    val displayPostLenght = _selectedPost.value?.lenght.plus("cm")
    val displayPostHeight = _selectedPost.value?.height.plus("cm")
    val displayPostWidth = _selectedPost.value?.width.plus("cm")


}