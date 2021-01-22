package com.example.swapbook.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapbook.database.BookDatabaseDao
import com.example.swapbook.database.BookDetail
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.swapbook.network.SwapBookApi

class HomeViewModel(
    dataSource: BookDatabaseDao,
    application: Application
) : ViewModel() {

    val database = dataSource

    val books = database.getAllBooks()

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response


    init {
        initializeAll()
//        getSwapBookProperties()
    }

    private fun getSwapBookProperties() {

//        SwapBookApi.retrofitService.getProperties().enqueue(
//                object: Callback<String> {
//                    override fun onResponse(call: Call<String>,
//                                            response: Response<String>) {
//                        _response.value = response.body()
//                    }
//
//                    override fun onFailure(call: Call<String>, t: Throwable) {
//                        _response.value = "Failure: " + t.message
//                    }
//                })

    }

    private fun initializeAll() {
        viewModelScope.launch {
        }

    }

    private suspend fun insert(book: BookDetail) {
        database.insert(book)
    }
}