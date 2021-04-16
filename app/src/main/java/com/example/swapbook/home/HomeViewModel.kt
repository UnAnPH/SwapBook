package com.example.swapbook.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapbook.database.BookDatabaseDao
import com.example.swapbook.database.Genre
import com.example.swapbook.network.Post
import kotlinx.coroutines.launch
import com.example.swapbook.network.SwapBookApi

enum class MarsApiStatus { LOADING, ERROR, DONE }

class HomeViewModel(
    dataSource: BookDatabaseDao,
    application: Application
) : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<MarsApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<MarsApiStatus>
        get() = _status

    private val _posts = MutableLiveData<List<Post>>()

    val posts: LiveData<List<Post>>
        get() = _posts

    private val _navigateToSelectedPost = MutableLiveData<Post>()
    val navigateToSelectedPost: LiveData<Post>
        get() = _navigateToSelectedPost

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties()

    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMarsRealEstateProperties() {
        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _posts.value = SwapBookApi.retrofitService.retrieve()
                _status.value = MarsApiStatus.DONE
                Log.i("msg", "bravo")
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _posts.value = ArrayList()
                Log.i("msg", e.toString())
            }
        }
    }



    fun displayPostDetails(post: Post) {
        _navigateToSelectedPost.value = post
    }

    fun displayPostDetailsComplete() {
        _navigateToSelectedPost.value = null
    }


}