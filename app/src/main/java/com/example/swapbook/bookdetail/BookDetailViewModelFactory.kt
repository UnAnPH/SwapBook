package com.example.swapbook.bookdetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swapbook.network.MarsProperty
import com.example.swapbook.network.Post

class BookDetailViewModelFactory (
    private val post: Post,
    private val application: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BookDetailViewModel::class.java)) {
                return BookDetailViewModel(post, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}
