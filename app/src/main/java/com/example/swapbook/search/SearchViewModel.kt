package com.example.swapbook.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapbook.database.BookDatabaseDao
import com.example.swapbook.network.Post
import com.example.swapbook.home.HomeViewModel
import com.example.swapbook.home.MarsApiStatus
import com.example.swapbook.network.SwapBookApi
import kotlinx.coroutines.launch

class SearchViewModel(
    dataSource: BookDatabaseDao,
    application: Application
)  : ViewModel() {
    // TODO: Implement the ViewModel
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<MarsApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<MarsApiStatus>
        get() = _status

    private var _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts

    var postsSearch: List<Post> = emptyList()

    private val _navigateToSelectedPost = MutableLiveData<Post>()
    val navigateToSelectedPost: LiveData<Post>
        get() = _navigateToSelectedPost

    init {
        getMarsRealEstateProperties()
        Log.i("ciao","2")
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMarsRealEstateProperties() {
        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _posts.value = SwapBookApi.retrofitService.retrieve()
                postsSearch = SwapBookApi.retrofitService.retrieve()
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _posts.value = ArrayList()
            }
        }
    }

    fun displayPostDetails(post: Post) {
        _navigateToSelectedPost.value = post
    }

    fun displayPostDetailsComplete() {
        _navigateToSelectedPost.value = null
    }

    fun setPosts(ciao: List<Post>){
        _posts.value = ciao
    }
}