package com.example.swapbook.bookdetail

import android.app.Application
import androidx.lifecycle.*
import com.example.swapbook.R
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

//    val displayPostPrice = Transformations.map(selectedPost) {
//        app.applicationContext.getString(
//            R.string.display_price, it.price)
//    }

    val displayPostPrice = _selectedPost.value?.price.plus("€")
    val displayPostLenght = _selectedPost.value?.lenght.plus("cm")
    val displayPostHeight = _selectedPost.value?.height.plus("cm")
    val displayPostWidth = _selectedPost.value?.width.plus("cm")
}