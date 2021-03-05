package com.example.swapbook.bookdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swapbook.network.MarsProperty
import com.example.swapbook.network.Post

class BookDetailViewModel(post: Post,
                      app: Application) : AndroidViewModel(app) {

    private val _selectedPost = MutableLiveData<Post>()
    val selectedPost: LiveData<Post>
        get() = _selectedPost

    init {
        _selectedPost.value = post
    }
}