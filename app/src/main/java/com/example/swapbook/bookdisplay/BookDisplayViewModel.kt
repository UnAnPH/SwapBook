package com.example.swapbook.bookdisplay

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapbook.MainActivity
import com.example.swapbook.network.MarsProperty
import com.example.swapbook.network.SwapBookApi
import com.example.swapbook.network.SwapBookApi.retrofitService
import com.example.swapbook.network.SwapBookApiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class MarsApiStatus { LOADING, ERROR, DONE }

class BookDisplayViewModel : ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()

    val status: LiveData<MarsApiStatus>
        get() = _status

    private val _property = MutableLiveData<MarsProperty>()

    val property: LiveData<MarsProperty>
        get() = _property

    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties: LiveData<List<MarsProperty>>
        get() = _properties

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
                _properties.value = SwapBookApi.retrofitService.getProperties()
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

}