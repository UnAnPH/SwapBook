package com.example.swapbook.insertion

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swapbook.bookdetail.BookDetailViewModel
import com.example.swapbook.network.Post

class InsertionViewModelFactory (
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InsertionViewModel::class.java)) {
            return InsertionViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}